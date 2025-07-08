package ing.beribtur.storejpa.feature.shared.customstore;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.item.entity.ProductImage;
import ing.beribtur.feature.shared.customstore.ProductCustomStore;
import ing.beribtur.feature.shared.sdo.PopularProductRdo;
import ing.beribtur.feature.shared.sdo.ProductRdo;
import ing.beribtur.feature.shared.sdo.ProductSearchQdo;
import ing.beribtur.feature.shared.sdo.ProductVariantRdo;
import ing.beribtur.storejpa.aggregate.item.jpo.ProductImageJpo;
import ing.beribtur.storejpa.aggregate.item.jpo.ProductVariantJpo;
import ing.beribtur.storejpa.feature.shared.customstore.pdo.ProductRdoPdo;
import ing.beribtur.storejpa.util.QuerydslUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ing.beribtur.storejpa.aggregate.item.jpo.QProductCategoryJpo.productCategoryJpo;
import static ing.beribtur.storejpa.aggregate.item.jpo.QProductImageJpo.productImageJpo;
import static ing.beribtur.storejpa.aggregate.item.jpo.QProductJpo.productJpo;
import static ing.beribtur.storejpa.aggregate.item.jpo.QProductVariantJpo.productVariantJpo;
import static ing.beribtur.storejpa.aggregate.rental.jpo.QRentalRecordJpo.rentalRecordJpo;
import static ing.beribtur.storejpa.aggregate.review.jpo.QReviewJpo.reviewJpo;
import static ing.beribtur.storejpa.aggregate.user.jpo.QLenderJpo.lenderJpo;

@Repository
@RequiredArgsConstructor
public class ProductQueryDslStore implements ProductCustomStore {
    //
    public static final double RENTAL_WEIGHT = 0.7;
    public static final double RATING_WEIGHT = 0.3;
    public static final int DEFAULT_LIMIT = 20;

    private final JPAQueryFactory jpaQueryFactory;
    private final QuerydslUtil querydslUtil;

    @Override
    public Page<ProductRdo> findProductRdos(ProductSearchQdo qdo, Offset offset) {
        //
        BooleanBuilder whereClause = new BooleanBuilder();

        boolean hasVariantFilters = hasProductVariantFilters(qdo);

        // Text search
        if (StringUtils.hasText(qdo.getSearchKeyword())) {
            whereClause.and(
                    productJpo.title.containsIgnoreCase(qdo.getSearchKeyword())
                            .or(productJpo.description.containsIgnoreCase(qdo.getSearchKeyword())));
            if (hasVariantFilters) {
                whereClause.or(
                                productVariantJpo.brand.containsIgnoreCase(qdo.getSearchKeyword()))
                        .or(productVariantJpo.model.containsIgnoreCase(qdo.getSearchKeyword()));
            }
        }

        // Product filters
        if (qdo.getOwnerIds() != null && !qdo.getOwnerIds().isEmpty()) {
            whereClause.and(productJpo.ownerId.in(qdo.getOwnerIds()));
        }

        if (qdo.getCategoryIds() != null && !qdo.getCategoryIds().isEmpty()) {
            whereClause.and(productJpo.categoryId.in(qdo.getCategoryIds()));
        }

        // Product variant filters
        if (qdo.getBrands() != null && !qdo.getBrands().isEmpty()) {
            whereClause.and(productVariantJpo.brand.in(qdo.getBrands()));
        }

        if (qdo.getModels() != null && !qdo.getModels().isEmpty()) {
            whereClause.and(productVariantJpo.model.in(qdo.getModels()));
        }

        if (qdo.getManufacturers() != null && !qdo.getManufacturers().isEmpty()) {
            whereClause.and(productVariantJpo.manufacturer.in(qdo.getManufacturers()));
        }

        if (qdo.getColors() != null && !qdo.getColors().isEmpty()) {
            whereClause.and(productVariantJpo.color.in(qdo.getColors()));
        }

        if (qdo.getMaterials() != null && !qdo.getMaterials().isEmpty()) {
            whereClause.and(productVariantJpo.material.in(qdo.getMaterials()));
        }

        if (qdo.getMadeInCountries() != null && !qdo.getMadeInCountries().isEmpty()) {
            whereClause.and(productVariantJpo.madeIn.in(qdo.getMadeInCountries()));
        }

        if (qdo.getProducedYears() != null && !qdo.getProducedYears().isEmpty()) {
            whereClause.and(productVariantJpo.producedYear.in(qdo.getProducedYears()));
        }

        // Price filters
        if (qdo.getMinPrice() != null) {
            whereClause.and(productVariantJpo.priceAmount.goe(qdo.getMinPrice()));
        }

        if (qdo.getMaxPrice() != null) {
            whereClause.and(productVariantJpo.priceAmount.loe(qdo.getMaxPrice()));
        }

        if (qdo.getPriceUnit() != null) {
            whereClause.and(productVariantJpo.priceUnit.eq(qdo.getPriceUnit().name()));
        }


        // Availability filters
        if (qdo.getActive() != null) {
            if (hasVariantFilters)
                whereClause.and(productVariantJpo.active.eq(qdo.getActive()));
            whereClause.and(productJpo.active.eq(qdo.getActive()));
        }

        if (qdo.getAvailableFrom() != null) {
            whereClause.and(
                    productVariantJpo.availableFrom.isNotNull()
                            .and(productVariantJpo.availableFrom.goe(qdo.getAvailableFrom()))
            );
        }

        if (qdo.getAvailableUntil() != null) {
            whereClause.and(
                    productVariantJpo.availableUntil.isNotNull()
                            .and(productVariantJpo.availableUntil.loe(qdo.getAvailableUntil()))
            );
        }


        if (qdo.getIsAvailable() != null) {
            LocalDateTime now = LocalDateTime.now();
            if (qdo.getIsAvailable()) {
                whereClause.and(productVariantJpo.active.isTrue())
                        .and(productVariantJpo.availableFrom.isNull().or(productVariantJpo.availableFrom.loe(now)))
                        .and(productVariantJpo.availableUntil.isNull().or(productVariantJpo.availableUntil.goe(now)));
            } else {
                whereClause.and(
                        productVariantJpo.active.isFalse()
                                .or(productVariantJpo.availableFrom.isNotNull().and(productVariantJpo.availableFrom.gt(now)))
                                .or(productVariantJpo.availableUntil.isNotNull().and(productVariantJpo.availableUntil.lt(now)))
                );
            }
        }


        OrderSpecifier<?>[] orderSpecifiers = {
                productJpo.registeredOn.desc()
        };

        JPAQuery<?> from;

        if (hasVariantFilters) {
            from = jpaQueryFactory
                    .from(productJpo)
                    .leftJoin(productCategoryJpo).on(productJpo.categoryId.eq(productCategoryJpo.id))
                    .innerJoin(productVariantJpo).on(productJpo.id.eq(productVariantJpo.productId))
                    .where(whereClause)
                    .distinct();
        } else {
            from = jpaQueryFactory
                    .from(productJpo)
                    .leftJoin(productCategoryJpo).on(productJpo.categoryId.eq(productCategoryJpo.id))
                    .where(whereClause)
                    .distinct();
        }

        Predicate[] predicates = whereClause.hasValue() ? new Predicate[]{whereClause} : new Predicate[0];

        Page<ProductRdoPdo> productRdoPdoPage = querydslUtil.query(
                Projections.constructor(ProductRdoPdo.class, productJpo, productCategoryJpo),
                from,
                predicates,
                orderSpecifiers,
                offset
        );

        return productRdoPdoPage.map(pdo -> pdo.toRdo(this::findProductVariantRdos));
    }

    @Override
    public ProductRdo findProductRdo(String productId) {
        //
        ProductRdoPdo productRdoPdo = jpaQueryFactory
                .select(Projections.constructor(ProductRdoPdo.class, productJpo, productCategoryJpo))
                .from(productJpo)
                .leftJoin(productCategoryJpo).on(productJpo.categoryId.eq(productCategoryJpo.id))
                .where(productJpo.id.eq(productId))
                .fetchOne();

        if (productRdoPdo == null) {
            return null;
        }

        return productRdoPdo.toRdo(this::findProductVariantRdos);
    }

    @Override
    public Page<PopularProductRdo> findPopularProductRdos(Integer maxCount, Offset offset) {
        // Validate and set pagination parameters
        int limit = (offset != null && offset.getLimit() > 0) ? offset.getLimit() : DEFAULT_LIMIT;
        int offsetVal = (offset != null && offset.getOffset() >= 0) ? offset.getOffset() : 0;

        // Step 1: Get normalization bounds with a lightweight query
        List<Tuple> boundsData = jpaQueryFactory
                .select(
                        rentalRecordJpo.id.countDistinct().as("rentalCount"),
                        reviewJpo.rating.avg().coalesce(0.0).as("averageRating")
                )
                .from(productVariantJpo)
                .join(productJpo).on(productVariantJpo.productId.eq(productJpo.id))
                .leftJoin(rentalRecordJpo).on(rentalRecordJpo.productVariantId.eq(productVariantJpo.id))
                .leftJoin(reviewJpo).on(reviewJpo.recordId.eq(rentalRecordJpo.id).and(reviewJpo.visible.isTrue()))
                .leftJoin(lenderJpo).on(lenderJpo.id.eq(productJpo.ownerId))
                .where(productJpo.active.isTrue()
                        .and(productVariantJpo.active.isTrue())
                        .and(lenderJpo.active.isTrue()))
                .groupBy(productJpo.id)
                .fetch();

        if (boundsData.isEmpty()) {
            return Page.empty(PageRequest.of(offsetVal / limit, limit));
        }

        // Calculate normalization bounds
        long minRental = Long.MAX_VALUE, maxRental = 0;
        double minRating = Double.MAX_VALUE, maxRating = 0.0;

        for (Tuple tuple : boundsData) {
            long rentalCount = Optional.ofNullable(tuple.get(0, Long.class)).orElse(0L);
            double rating = Optional.ofNullable(tuple.get(1, Double.class)).orElse(0.0);

            minRental = Math.min(minRental, rentalCount);
            maxRental = Math.max(maxRental, rentalCount);
            minRating = Math.min(minRating, rating);
            maxRating = Math.max(maxRating, rating);
        }

        // Handle edge cases to prevent division by zero
        if (maxRental == minRental) {
            maxRental = minRental + 1;
        }
        if (Double.compare(maxRating, minRating) == 0) {
            maxRating = minRating + 1.0;
        }


        // Step 2: Main query with popularity calculation in database
        NumberExpression<Double> rentalCountNormalized = rentalRecordJpo.id.countDistinct()
                .subtract(minRental)
                .castToNum(Double.class)
                .divide(maxRental - minRental);

        NumberExpression<Double> ratingNormalized = reviewJpo.rating.avg().coalesce(0.0)
                .subtract(minRating)
                .divide(maxRating - minRating);

        NumberExpression<Double> popularityScore = rentalCountNormalized.multiply(RENTAL_WEIGHT)
                .add(ratingNormalized.multiply(RATING_WEIGHT));

        // Get total count for pagination
        Long totalCountResult = jpaQueryFactory
                .select(productJpo.id.countDistinct())
                .from(productVariantJpo)
                .join(productJpo).on(productVariantJpo.productId.eq(productJpo.id))
                .leftJoin(lenderJpo).on(lenderJpo.id.eq(productJpo.ownerId))
                .where(productJpo.active.isTrue()
                        .and(productVariantJpo.active.isTrue())
                        .and(lenderJpo.active.isTrue()))
                .fetchOne();

        long totalCount = Optional.ofNullable(totalCountResult).orElse(0L);

        // Apply maxCount to total if specified
        long totalElementsToReturn = (maxCount != null && maxCount > 0) ?
                Math.min(maxCount, totalCount) : totalCount;

        // Main query with database-level sorting and pagination
        List<Tuple> results = jpaQueryFactory
                .select(
                        productJpo.id,
                        productJpo.title,
                        productVariantJpo.priceAmount,
                        productVariantJpo.priceCurrency,
                        productVariantJpo.priceUnit,
                        productImageJpo.url.min().as("imageUrl"),
                        lenderJpo.address,
                        rentalRecordJpo.id.countDistinct().as("rentalCount"),
                        reviewJpo.rating.avg().coalesce(0.0).as("averageRating"),
                        reviewJpo.id.countDistinct().as("reviewCount"),
                        popularityScore.as("popularityScore")
                )
                .from(productVariantJpo)
                .join(productJpo).on(productVariantJpo.productId.eq(productJpo.id))
                .leftJoin(rentalRecordJpo).on(rentalRecordJpo.productVariantId.eq(productVariantJpo.id))
                .leftJoin(reviewJpo).on(reviewJpo.recordId.eq(rentalRecordJpo.id).and(reviewJpo.visible.isTrue()))
                .leftJoin(productImageJpo).on(productImageJpo.variantId.eq(productVariantJpo.id)
                        .and(productImageJpo.active.isTrue()))
                .leftJoin(lenderJpo).on(lenderJpo.id.eq(productJpo.ownerId))
                .where(productJpo.active.isTrue()
                        .and(productVariantJpo.active.isTrue())
                        .and(lenderJpo.active.isTrue()))
                .groupBy(productJpo.id, productJpo.title, productVariantJpo.priceAmount,
                        productVariantJpo.priceCurrency, productVariantJpo.priceUnit, lenderJpo.address)
                .orderBy(popularityScore.desc(), productJpo.id.asc()) // Secondary sort for consistency
                .offset(offsetVal)
                .limit(Math.max(0, Math.min(limit, (int) (totalElementsToReturn - offsetVal))))
                .fetch();

        // Step 3: Map results to DTOs
        List<PopularProductRdo> products = results.stream()
                .map(tuple -> {
                    double avgRating = Optional.ofNullable(tuple.get(8, Double.class)).orElse(0.0);

                    return PopularProductRdo.builder()
                            .productId(tuple.get(0, String.class))
                            .title(tuple.get(1, String.class))
                            .averageRating((int) Math.round(avgRating))
                            .reviewCount(Optional.ofNullable(tuple.get(9, Long.class)).orElse(0L).intValue())
                            .url(tuple.get(5, String.class))
                            .priceAmount(tuple.get(2, BigDecimal.class))
                            .priceCurrency(tuple.get(3, String.class))
                            .priceUnit(tuple.get(4, String.class))
                            .address(tuple.get(6, String.class))
                            .build();
                })
                .collect(Collectors.toList());

        // Create proper pageable object
        Pageable pageable = PageRequest.of(offsetVal / limit, limit);
        return new PageImpl<>(products, pageable, totalElementsToReturn);
    }

    private List<ProductVariantRdo> findProductVariantRdos(String productId) {
        //
        // Fetch all variants for the product
        List<ProductVariantJpo> variantJpos = jpaQueryFactory
                .selectFrom(productVariantJpo)
                .where(productVariantJpo.productId.eq(productId))
                .orderBy(productVariantJpo.registeredOn.asc())
                .fetch();

        // Convert each variant to RDO with its images
        return variantJpos.stream()
                .map(this::convertVariantToRdo)
                .toList();
    }

    private ProductVariantRdo convertVariantToRdo(ProductVariantJpo variantJpo) {
        //
        // Fetch images for this variant
        List<ProductImageJpo> imageJpos = jpaQueryFactory
                .selectFrom(productImageJpo)
                .where(productImageJpo.variantId.eq(variantJpo.getId()))
                .orderBy(productImageJpo.order.asc())
                .fetch();

        // Convert to domain objects
        List<ProductImage> images = imageJpos.stream()
                .map(ProductImageJpo::toDomain)
                .toList();

        return ProductVariantRdo.builder()
                .variant(variantJpo.toDomain())
                .images(images)
                .build();
    }

    private boolean hasProductVariantFilters(ProductSearchQdo qdo) {
        //
        return (qdo.getBrands() != null && !qdo.getBrands().isEmpty()) ||
                (qdo.getModels() != null && !qdo.getModels().isEmpty()) ||
                (qdo.getManufacturers() != null && !qdo.getManufacturers().isEmpty()) ||
                (qdo.getColors() != null && !qdo.getColors().isEmpty()) ||
                (qdo.getMaterials() != null && !qdo.getMaterials().isEmpty()) ||
                (qdo.getMadeInCountries() != null && !qdo.getMadeInCountries().isEmpty()) ||
                (qdo.getProducedYears() != null && !qdo.getProducedYears().isEmpty()) ||
                qdo.getMinPrice() != null ||
                qdo.getMaxPrice() != null ||
                qdo.getPriceUnit() != null ||
                qdo.getAvailableFrom() != null ||
                qdo.getAvailableUntil() != null ||
                qdo.getIsAvailable() != null ||
                qdo.getHasVariants() != null ||
                qdo.getHasImages() != null;
    }
}
