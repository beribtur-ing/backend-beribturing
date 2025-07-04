package ing.beribtur.storejpa.shared.customstore;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.rental.entity.vo.ReservationStatus;
import ing.beribtur.feature.shared.customstore.ReservationCustomStore;
import ing.beribtur.feature.shared.sdo.ReservationRdo;
import ing.beribtur.storejpa.aggregate.item.jpo.ProductJpo;
import ing.beribtur.storejpa.aggregate.item.jpo.ProductVariantJpo;
import ing.beribtur.storejpa.aggregate.rental.jpo.ReservationJpo;
import ing.beribtur.storejpa.aggregate.user.jpo.LendeeJpo;
import ing.beribtur.storejpa.aggregate.user.jpo.LenderJpo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ing.beribtur.storejpa.aggregate.item.jpo.QProductJpo.productJpo;
import static ing.beribtur.storejpa.aggregate.item.jpo.QProductVariantJpo.productVariantJpo;
import static ing.beribtur.storejpa.aggregate.rental.jpo.QReservationJpo.reservationJpo;
import static ing.beribtur.storejpa.aggregate.user.jpo.QLendeeJpo.lendeeJpo;
import static ing.beribtur.storejpa.aggregate.user.jpo.QLenderJpo.lenderJpo;

@Repository
@RequiredArgsConstructor
public class ReservationQueryDslStore implements ReservationCustomStore {
    //
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<ReservationRdo> findReservationRdos(String ownerId, ReservationStatus status, Offset offset) {
        //
        Predicate predicate = buildReservationPredicate(ownerId, status);

        // Main query with pagination
        List<ReservationJpo> reservationJpos = createBaseQuery()
            .where(predicate)
            .orderBy(reservationJpo.registeredOn.desc())
            .offset(offset.getOffset())
            .limit(offset.getLimit())
            .fetch();

        if (reservationJpos.isEmpty()) {
            return Page.empty(createPageable(offset));
        }

        // Count query (only if we have results)
        Long totalCountResult = jpaQueryFactory
            .select(reservationJpo.count())
            .from(reservationJpo)
            .leftJoin(productVariantJpo).on(reservationJpo.productVariantId.eq(productVariantJpo.id))
            .leftJoin(productJpo).on(productVariantJpo.productId.eq(productJpo.id))
            .where(predicate)
            .fetchOne();

        long totalCount = totalCountResult != null ? totalCountResult : 0L;

        // Build response efficiently
        List<ReservationRdo> reservationRdos = buildReservationRdos(reservationJpos);

        return new PageImpl<>(reservationRdos, createPageable(offset), totalCount);
    }

    private JPAQuery<ReservationJpo> createBaseQuery() {
        //
        return jpaQueryFactory
            .select(reservationJpo)
            .from(reservationJpo)
            .leftJoin(productVariantJpo).on(reservationJpo.productVariantId.eq(productVariantJpo.id))
            .leftJoin(productJpo).on(productVariantJpo.productId.eq(productJpo.id));
    }

    private Predicate buildReservationPredicate(String ownerId, ReservationStatus status) {
        //
        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasText(ownerId)) {
            builder.and(reservationJpo.ownerId.eq(ownerId));
        }

        if (status != null) {
            builder.and(reservationJpo.status.eq(status.name()));
        }

        return builder;
    }

    private List<ReservationRdo> buildReservationRdos(List<ReservationJpo> reservationJpos) {
        // Extract all required IDs upfront
        Set<String> lendeeIds = extractIds(reservationJpos, ReservationJpo::getRequesterId);
        Set<String> lenderIds = extractIds(reservationJpos, ReservationJpo::getOwnerId);
        Set<String> productVariantIds = extractIds(reservationJpos, ReservationJpo::getProductVariantId);

        // Batch fetch all related entities
        Map<String, LendeeJpo> lendeeMap = fetchLendeesByIds(lendeeIds);
        Map<String, LenderJpo> lenderMap = fetchLendersByIds(lenderIds);
        Map<String, ProductVariantJpo> productVariantMap = fetchProductVariantsByIds(productVariantIds);

        // Extract product IDs from variants
        Set<String> productIds = productVariantMap.values().stream()
            .map(ProductVariantJpo::getProductId)
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());

        Map<String, ProductJpo> productMap = fetchProductsByIds(productIds);

        // Convert JPOs to RDOs
        return reservationJpos.stream()
            .map(jpo -> buildReservationRdo(jpo, lendeeMap, lenderMap, productVariantMap, productMap))
            .collect(Collectors.toList());
    }

    private <T> Set<String> extractIds(List<ReservationJpo> jpos, Function<ReservationJpo, String> idExtractor) {
        //
        return jpos.stream()
            .map(idExtractor)
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());
    }

    private ReservationRdo buildReservationRdo(ReservationJpo reservationJpo,
                                               Map<String, LendeeJpo> lendeeMap,
                                               Map<String, LenderJpo> lenderMap,
                                               Map<String, ProductVariantJpo> productVariantMap,
                                               Map<String, ProductJpo> productMap) {
        //
        ReservationRdo.ReservationRdoBuilder builder = ReservationRdo.builder()
            .id(reservationJpo.getId())
            .period(reservationJpo.toDomain().getPeriod())
            .status(ReservationStatus.valueOf(reservationJpo.getStatus()));

        // Set requester name
        Optional.ofNullable(lendeeMap.get(reservationJpo.getRequesterId()))
            .ifPresent(lendee -> builder.requesterName(lendee.getName()));

        // Set owner name
        Optional.ofNullable(lenderMap.get(reservationJpo.getOwnerId()))
            .ifPresent(lender -> builder.ownerName(lender.getName()));

        // Set product variant information
        Optional.ofNullable(productVariantMap.get(reservationJpo.getProductVariantId()))
            .ifPresent(variant -> {
                builder.variantId(variant.getId())
                    .variantBrand(variant.getBrand())
                    .variantModel(variant.getModel())
                    .variantColor(variant.getColor())
                    .variantSize(variant.toDomain().getSize());

                // Set product information
                Optional.ofNullable(productMap.get(variant.getProductId()))
                    .ifPresent(product -> {
                        builder.productId(product.getId())
                            .productName(product.getTitle());
                    });
            });

        return builder.build();
    }

    private Pageable createPageable(Offset offset) {
        //
        if (offset.getSortDirection() != null && offset.getSortingField() != null) {
            return PageRequest.of(offset.page(), offset.limit(),
                (offset.ascendingSort() ? Sort.Direction.ASC : Sort.Direction.DESC),
                offset.getSortingField());
        } else {
            return PageRequest.of(offset.page(), offset.limit());
        }
    }

    // Optimized batch fetch methods
    private Map<String, LendeeJpo> fetchLendeesByIds(Set<String> lendeeIds) {
        //
        return lendeeIds.isEmpty() ? Collections.emptyMap() :
            jpaQueryFactory.selectFrom(lendeeJpo)
                .where(lendeeJpo.id.in(lendeeIds))
                .fetch()
                .stream()
                .collect(Collectors.toMap(LendeeJpo::getId, Function.identity()));
    }

    private Map<String, LenderJpo> fetchLendersByIds(Set<String> lenderIds) {
        //
        return lenderIds.isEmpty() ? Collections.emptyMap() :
            jpaQueryFactory.selectFrom(lenderJpo)
                .where(lenderJpo.id.in(lenderIds))
                .fetch()
                .stream()
                .collect(Collectors.toMap(LenderJpo::getId, Function.identity()));
    }

    private Map<String, ProductVariantJpo> fetchProductVariantsByIds(Set<String> productVariantIds) {
        //
        return productVariantIds.isEmpty() ? Collections.emptyMap() :
            jpaQueryFactory.selectFrom(productVariantJpo)
                .where(productVariantJpo.id.in(productVariantIds))
                .fetch()
                .stream()
                .collect(Collectors.toMap(ProductVariantJpo::getId, Function.identity()));
    }

    private Map<String, ProductJpo> fetchProductsByIds(Set<String> productIds) {
        //
        return productIds.isEmpty() ? Collections.emptyMap() :
            jpaQueryFactory.selectFrom(productJpo)
                .where(productJpo.id.in(productIds))
                .fetch()
                .stream()
                .collect(Collectors.toMap(ProductJpo::getId, Function.identity()));
    }
}
