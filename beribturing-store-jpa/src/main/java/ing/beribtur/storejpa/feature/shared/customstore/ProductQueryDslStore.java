package ing.beribtur.storejpa.feature.shared.customstore;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.item.entity.ProductImage;
import ing.beribtur.feature.shared.customstore.ProductCustomStore;
import ing.beribtur.feature.shared.sdo.ProductRdo;
import ing.beribtur.feature.shared.sdo.ProductSearchQdo;
import ing.beribtur.feature.shared.sdo.ProductVariantRdo;
import ing.beribtur.storejpa.aggregate.item.jpo.ProductImageJpo;
import ing.beribtur.storejpa.aggregate.item.jpo.ProductVariantJpo;
import ing.beribtur.storejpa.feature.shared.customstore.pdo.ProductRdoPdo;
import ing.beribtur.storejpa.util.QuerydslUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static ing.beribtur.storejpa.aggregate.item.jpo.QProductCategoryJpo.productCategoryJpo;
import static ing.beribtur.storejpa.aggregate.item.jpo.QProductImageJpo.productImageJpo;
import static ing.beribtur.storejpa.aggregate.item.jpo.QProductJpo.productJpo;
import static ing.beribtur.storejpa.aggregate.item.jpo.QProductVariantJpo.productVariantJpo;

@Repository
@RequiredArgsConstructor
public class ProductQueryDslStore implements ProductCustomStore {
    //
    private final JPAQueryFactory jpaQueryFactory;
    private final QuerydslUtil querydslUtil;

    @Override
    public Page<ProductRdo> findProductRdos(ProductSearchQdo qdo, Offset offset) {
        //
        BooleanBuilder whereClause = new BooleanBuilder();

        // Text search
        if (StringUtils.hasText(qdo.getSearchKeyword())) {
            whereClause.and(
                productJpo.title.containsIgnoreCase(qdo.getSearchKeyword())
                    .or(productJpo.description.containsIgnoreCase(qdo.getSearchKeyword()))
                    .or(productVariantJpo.brand.containsIgnoreCase(qdo.getSearchKeyword()))
                    .or(productVariantJpo.model.containsIgnoreCase(qdo.getSearchKeyword()))
            );
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
            whereClause.and(productVariantJpo.active.eq(qdo.getActive()));
        }

        if (qdo.getAvailableFrom() != null) {
            whereClause.and(productVariantJpo.availableFrom.goe(qdo.getAvailableFrom()));
        }

        if (qdo.getAvailableUntil() != null) {
            whereClause.and(productVariantJpo.availableUntil.loe(qdo.getAvailableUntil()));
        }

        if (qdo.getIsAvailable() != null) {
            if (qdo.getIsAvailable()) {
                // Product is currently available
                whereClause.and(productVariantJpo.active.isTrue())
                    .and(productVariantJpo.availableFrom.loe(java.time.LocalDateTime.now()))
                    .and(productVariantJpo.availableUntil.goe(java.time.LocalDateTime.now()));
            } else {
                // Product is not currently available
                whereClause.and(productVariantJpo.active.isFalse()
                    .or(productVariantJpo.availableFrom.gt(java.time.LocalDateTime.now()))
                    .or(productVariantJpo.availableUntil.lt(java.time.LocalDateTime.now())));
            }
        }

        // Date filters
        if (qdo.getCreatedAfter() != null) {
            whereClause.and(productJpo.registeredOn.after(qdo.getCreatedAfter()));
        }

        if (qdo.getCreatedBefore() != null) {
            whereClause.and(productJpo.registeredOn.before(qdo.getCreatedBefore()));
        }

        if (qdo.getUpdatedAfter() != null) {
            whereClause.and(productJpo.modifiedOn.after(qdo.getUpdatedAfter()));
        }

        if (qdo.getUpdatedBefore() != null) {
            whereClause.and(productJpo.modifiedOn.before(qdo.getUpdatedBefore()));
        }

        // Administrative filters
        if (qdo.getHasVariants() != null) {
            if (qdo.getHasVariants()) {
                // Products that have at least one variant
                whereClause.and(productVariantJpo.id.isNotNull());
            } else {
                // Products that have no variants
                whereClause.and(productVariantJpo.id.isNull());
            }
        }

        if (qdo.getHasImages() != null) {
            if (qdo.getHasImages()) {
                // Products that have at least one image (via their variants)
                whereClause.and(jpaQueryFactory
                    .selectOne()
                    .from(productImageJpo)
                    .where(productImageJpo.variantId.eq(productVariantJpo.id))
                    .exists());
            } else {
                // Products that have no images
                whereClause.and(jpaQueryFactory
                    .selectOne()
                    .from(productImageJpo)
                    .where(productImageJpo.variantId.eq(productVariantJpo.id))
                    .notExists());
            }
        }

        OrderSpecifier<?>[] orderSpecifiers = {
            productJpo.registeredOn.desc()
        };

        JPAQuery<?> from = jpaQueryFactory
            .from(productJpo)
            .leftJoin(productCategoryJpo).on(productJpo.categoryId.eq(productCategoryJpo.id))
            .leftJoin(productVariantJpo).on(productJpo.id.eq(productVariantJpo.productId))
            .where(whereClause)
            .distinct();

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
}
