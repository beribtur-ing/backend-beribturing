package ing.beribtur.storejpa.shared.customstore;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.item.entity.vo.PriceUnit;
import ing.beribtur.aggregate.payment.entity.RentalDeposit;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.rental.entity.vo.RentalStatus;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.feature.shared.customstore.RentalRecordCustomStore;
import ing.beribtur.feature.shared.sdo.ProductRentalRecordRdo;
import ing.beribtur.feature.shared.sdo.RentalRecordRdo;
import ing.beribtur.storejpa.aggregate.item.jpo.ProductCategoryJpo;
import ing.beribtur.storejpa.aggregate.item.jpo.ProductJpo;
import ing.beribtur.storejpa.aggregate.item.jpo.ProductVariantJpo;
import ing.beribtur.storejpa.aggregate.item.jpo.ProductImageJpo;
import ing.beribtur.storejpa.aggregate.payment.jpo.RentalDepositJpo;
import ing.beribtur.storejpa.aggregate.rental.jpo.RentalRecordJpo;
import ing.beribtur.storejpa.aggregate.user.jpo.LendeeJpo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ing.beribtur.storejpa.aggregate.item.jpo.QProductCategoryJpo.productCategoryJpo;
import static ing.beribtur.storejpa.aggregate.item.jpo.QProductJpo.productJpo;
import static ing.beribtur.storejpa.aggregate.item.jpo.QProductVariantJpo.productVariantJpo;
import static ing.beribtur.storejpa.aggregate.item.jpo.QProductImageJpo.productImageJpo;
import static ing.beribtur.storejpa.aggregate.payment.jpo.QRentalDepositJpo.rentalDepositJpo;
import static ing.beribtur.storejpa.aggregate.rental.jpo.QRentalRecordJpo.rentalRecordJpo;
import static ing.beribtur.storejpa.aggregate.user.jpo.QLendeeJpo.lendeeJpo;

@Repository
@RequiredArgsConstructor
public class RentalRecordQueryDslStore implements RentalRecordCustomStore {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<RentalRecordRdo> findRentalRecordsByOwner(String ownerId, RentalStatus status, String searchKeyword, Offset offset) {
        //
        Predicate predicate = buildRentalRecordPredicate(ownerId, status, searchKeyword);

        // Main query with pagination
        List<RentalRecordJpo> rentalRecordJpos = createBaseQuery()
                .where(predicate)
                .orderBy(rentalRecordJpo.rentedAt.desc())
                .offset(offset.getOffset())
                .limit(offset.getLimit())
                .fetch();

        if (rentalRecordJpos.isEmpty()) {
            return Page.empty(createPageable(offset));
        }

        // Count query (only if we have results)
        Long totalCountResult = jpaQueryFactory
                .select(rentalRecordJpo.count())
                .from(rentalRecordJpo)
                .leftJoin(productVariantJpo).on(rentalRecordJpo.productVariantId.eq(productVariantJpo.id))
                .leftJoin(productJpo).on(productVariantJpo.productId.eq(productJpo.id))
                .where(predicate)
                .fetchOne();

        long totalCount = totalCountResult != null ? totalCountResult : 0L;

        // Build response efficiently
        List<RentalRecordRdo> rentalRecordRdos = buildRentalRecordRdos(rentalRecordJpos);

        return new PageImpl<>(rentalRecordRdos, createPageable(offset), totalCount);
    }

    @Override
    public Page<RentalRecordRdo> findRentalRecordsByLendee(String lendeeId, Offset offset) {
        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasText(lendeeId)) {
            builder.and(rentalRecordJpo.lendeeId.eq(lendeeId));
        }

        // Main query with pagination
        List<RentalRecordJpo> rentalRecordJpos = createBaseQuery()
                .where(builder)
                .orderBy(rentalRecordJpo.rentedAt.desc())
                .offset(offset.getOffset())
                .limit(offset.getLimit())
                .fetch();

        if (rentalRecordJpos.isEmpty()) {
            return Page.empty(createPageable(offset));
        }

        // Count query (only if we have results)
        Long totalCountResult = jpaQueryFactory
                .select(rentalRecordJpo.count())
                .from(rentalRecordJpo)
                .leftJoin(productVariantJpo).on(rentalRecordJpo.productVariantId.eq(productVariantJpo.id))
                .leftJoin(productJpo).on(productVariantJpo.productId.eq(productJpo.id))
                .where(builder)
                .fetchOne();

        long totalCount = totalCountResult != null ? totalCountResult : 0L;

        // Build response efficiently
        List<RentalRecordRdo> rentalRecordRdos = buildRentalRecordRdos(rentalRecordJpos);

        return new PageImpl<>(rentalRecordRdos, createPageable(offset), totalCount);
    }

    private JPAQuery<RentalRecordJpo> createBaseQuery() {
        //
        return jpaQueryFactory
                .select(rentalRecordJpo)
                .from(rentalRecordJpo)
                .leftJoin(productVariantJpo).on(rentalRecordJpo.productVariantId.eq(productVariantJpo.id))
                .leftJoin(productJpo).on(productVariantJpo.productId.eq(productJpo.id));
    }

    private Predicate buildRentalRecordPredicate(String ownerId, RentalStatus status, String searchKeyword) {
        //
        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasText(ownerId)) {
            builder.and(rentalRecordJpo.ownerId.eq(ownerId));
        }

        if (status != null) {
            builder.and(rentalRecordJpo.status.eq(status.name()));
        }

        if (StringUtils.hasText(searchKeyword)) {
            builder.and(productJpo.title.containsIgnoreCase(searchKeyword));
        }

        return builder;
    }

    private List<RentalRecordRdo> buildRentalRecordRdos(List<RentalRecordJpo> rentalRecordJpos) {
        // Extract all required IDs upfront
        Set<String> lendeeIds = extractIds(rentalRecordJpos, RentalRecordJpo::getLendeeId);
        Set<String> productVariantIds = extractIds(rentalRecordJpos, RentalRecordJpo::getProductVariantId);
        Set<String> depositIds = extractIds(rentalRecordJpos, RentalRecordJpo::getDepositId);

        // Batch fetch all related entities
        Map<String, Lendee> lendeeMap = fetchLendeesByIds(lendeeIds);
        Map<String, ProductVariantJpo> productVariantMap = fetchProductVariantsByIds(productVariantIds);
        Map<String, RentalDeposit> rentalDepositMap = fetchRentalDepositsByIds(depositIds);
        Map<String, List<ProductImageJpo>> productImageMap = fetchProductImagesByVariantIds(productVariantIds);

        // Extract product and category IDs from variants
        Set<String> productIds = productVariantMap.values().stream()
                .map(ProductVariantJpo::getProductId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Map<String, ProductJpo> productMap = fetchProductsByIds(productIds);

        Set<String> categoryIds = productMap.values().stream()
                .map(ProductJpo::getCategoryId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Map<String, ProductCategoryJpo> categoryMap = fetchCategoriesByIds(categoryIds);

        // Convert JPOs to domains and build RDOs
        return rentalRecordJpos.stream()
                .map(RentalRecordJpo::toDomain)
                .map(domain -> buildRentalRecordRdo(domain, lendeeMap, productVariantMap,
                        productMap, categoryMap, rentalDepositMap, productImageMap))
                .collect(Collectors.toList());
    }

    private <T> Set<String> extractIds(List<RentalRecordJpo> jpos, Function<RentalRecordJpo, String> idExtractor) {
        //
        return jpos.stream()
                .map(idExtractor)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    private RentalRecordRdo buildRentalRecordRdo(RentalRecord rentalRecord,
                                                 Map<String, Lendee> lendeeMap,
                                                 Map<String, ProductVariantJpo> productVariantMap,
                                                 Map<String, ProductJpo> productMap,
                                                 Map<String, ProductCategoryJpo> categoryMap,
                                                 Map<String, RentalDeposit> rentalDepositMap,
                                                 Map<String, List<ProductImageJpo>> productImageMap) {
        //

        RentalRecordRdo.RentalRecordRdoBuilder builder = RentalRecordRdo.builder()
                .id(rentalRecord.getId())
                .period(rentalRecord.getPeriod())
                .rentedAt(rentalRecord.getRentedAt())
                .returnedAt(rentalRecord.getReturnedAt())
                .fee(rentalRecord.getFee())
                .status(rentalRecord.getStatus())
                .lendee(lendeeMap.get(rentalRecord.getLendeeId()))
                .rentalDeposit(rentalDepositMap.get(rentalRecord.getDepositId()));

        // Build product rental record RDO if product variant exists
        Optional.ofNullable(productVariantMap.get(rentalRecord.getProductVariantId()))
                .ifPresent(variant -> {
                    ProductJpo product = productMap.get(variant.getProductId());
                    if (product != null) {
                        ProductCategoryJpo category = categoryMap.get(product.getCategoryId());
                        List<ProductImageJpo> images = productImageMap.get(variant.getId());
                        builder.productRentalRecordRdo(createProductRentalRecordRdo(product, variant, category, images));
                    }
                });

        return builder.build();
    }

    private ProductRentalRecordRdo createProductRentalRecordRdo(ProductJpo product,
                                                                ProductVariantJpo variant,
                                                                ProductCategoryJpo category,
                                                                List<ProductImageJpo> images) {
        //
        return ProductRentalRecordRdo.builder()
                .productId(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .categoryId(category != null ? category.getId() : null)
                .name(category != null ? category.getName() : null)
                .productVariantId(variant.getId())
                .model(variant.getModel())
                .amount(variant.getPriceAmount())
                .currency(variant.getPriceCurrency())
                .unit(Optional.ofNullable(variant.getPriceUnit())
                        .map(PriceUnit::valueOf)
                        .orElse(null))
                .images(images != null ? images.stream().map(ProductImageJpo::toDomain).collect(Collectors.toList()) : Collections.emptyList())
                .build();
    }

    private Pageable createPageable(Offset offset) {
        //
        if (offset.getSortDirection() != null && offset.getSortingField() != null) {
            return PageRequest.of(offset.page(), offset.limit(), (offset.ascendingSort() ? Sort.Direction.ASC : Sort.Direction.DESC), offset.getSortingField());
        } else {
            return PageRequest.of(offset.page(), offset.limit());
        }
    }

    // Optimized batch fetch methods with Set parameters for better performance
    private Map<String, Lendee> fetchLendeesByIds(Set<String> lendeeIds) {
        //
        return lendeeIds.isEmpty() ? Collections.emptyMap() :
                jpaQueryFactory.selectFrom(lendeeJpo)
                        .where(lendeeJpo.id.in(lendeeIds))
                        .fetch()
                        .stream()
                        .collect(Collectors.toMap(LendeeJpo::getId, LendeeJpo::toDomain));
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

    private Map<String, ProductCategoryJpo> fetchCategoriesByIds(Set<String> categoryIds) {
        //
        return categoryIds.isEmpty() ? Collections.emptyMap() :
                jpaQueryFactory.selectFrom(productCategoryJpo)
                        .where(productCategoryJpo.id.in(categoryIds))
                        .fetch()
                        .stream()
                        .collect(Collectors.toMap(ProductCategoryJpo::getId, Function.identity()));
    }

    private Map<String, RentalDeposit> fetchRentalDepositsByIds(Set<String> depositIds) {
        //
        return depositIds.isEmpty() ? Collections.emptyMap() :
                jpaQueryFactory.selectFrom(rentalDepositJpo)
                        .where(rentalDepositJpo.id.in(depositIds))
                        .fetch()
                        .stream()
                        .collect(Collectors.toMap(RentalDepositJpo::getId, RentalDepositJpo::toDomain));
    }

    private Map<String, List<ProductImageJpo>> fetchProductImagesByVariantIds(Set<String> productVariantIds) {
        //
        return productVariantIds.isEmpty() ? Collections.emptyMap() :
                jpaQueryFactory.selectFrom(productImageJpo)
                        .where(productImageJpo.variantId.in(productVariantIds)
                                .and(productImageJpo.active.eq(true)))
                        .orderBy(productImageJpo.order.asc())
                        .fetch()
                        .stream()
                        .collect(Collectors.groupingBy(ProductImageJpo::getVariantId));
    }
}