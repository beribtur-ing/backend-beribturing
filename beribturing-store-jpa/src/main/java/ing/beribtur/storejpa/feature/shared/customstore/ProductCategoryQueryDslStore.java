package ing.beribtur.storejpa.feature.shared.customstore;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.feature.shared.customstore.ProductCategoryCustomStore;
import ing.beribtur.feature.shared.sdo.ProductCategoryRdo;
import ing.beribtur.storejpa.feature.shared.customstore.pdo.ProductCategoryRdoPdo;
import ing.beribtur.storejpa.util.QuerydslUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

import static ing.beribtur.storejpa.aggregate.item.jpo.QProductCategoryJpo.productCategoryJpo;


@Repository
@RequiredArgsConstructor
public class ProductCategoryQueryDslStore implements ProductCategoryCustomStore {
    //
    private final JPAQueryFactory jpaQueryFactory;
    private final QuerydslUtil querydslUtil;

    @Override
    public Page<ProductCategoryRdo> findProductCategoryRdos(String searchKeyword, Offset offset) {
        //
        return findProductCategoryRdos(searchKeyword, offset, false); // Default: show all (admin view)
    }

    @Override
    public Page<ProductCategoryRdo> findProductCategoryRdos(String searchKeyword, Offset offset, boolean activeOnly) {
        //
        Predicate searchPredicate = QuerydslUtil.likeIfNotNull(productCategoryJpo.name, searchKeyword)
            .or(QuerydslUtil.likeIfNotNull(productCategoryJpo.description, searchKeyword));
        
        Predicate[] predicates = activeOnly ? 
            new Predicate[]{searchPredicate, productCategoryJpo.active.eq(true)} :
            new Predicate[]{searchPredicate};
            
        OrderSpecifier<?>[] orderSpecifiers = {
            productCategoryJpo.registeredOn.desc()
        };

        JPAQuery<?> from = jpaQueryFactory.from(productCategoryJpo);

        Page<ProductCategoryRdoPdo> productCategoryRdoPdoPage = querydslUtil.query(
            Projections.constructor(ProductCategoryRdoPdo.class, productCategoryJpo),
            from,
            predicates,
            orderSpecifiers,
            offset
        );

        return productCategoryRdoPdoPage.map(pdo -> pdo.toRdo(parentId -> findSubCategories(parentId, activeOnly)));
    }

    @Override
    public ProductCategoryRdo findProductCategoryRdo(String categoryId) {
        //
        return findProductCategoryRdo(categoryId, false); // Default: show all (admin view)
    }

    @Override
    public ProductCategoryRdo findProductCategoryRdo(String categoryId, boolean activeOnly) {
        //
        Predicate predicate = activeOnly ?
            productCategoryJpo.id.eq(categoryId).and(productCategoryJpo.active.eq(true)) :
            productCategoryJpo.id.eq(categoryId);

        ProductCategoryRdoPdo productCategoryRdoPdo = jpaQueryFactory
            .select(Projections.constructor(ProductCategoryRdoPdo.class, productCategoryJpo))
            .from(productCategoryJpo)
            .where(predicate)
            .fetchOne();

        if (productCategoryRdoPdo == null) {
            return null;
        }

        return productCategoryRdoPdo.toRdo(parentId -> findSubCategories(parentId, activeOnly));
    }

    private List<ProductCategoryRdo> findSubCategories(String parentId) {
        //
        return findSubCategories(parentId, false); // Default: show all (admin view)
    }

    private List<ProductCategoryRdo> findSubCategories(String parentId, boolean activeOnly) {
        //
        Predicate predicate = activeOnly ?
            productCategoryJpo.parentId.eq(parentId).and(productCategoryJpo.active.eq(true)) :
            productCategoryJpo.parentId.eq(parentId);

        List<ProductCategoryRdoPdo> subCategoryPdos = jpaQueryFactory
            .select(Projections.constructor(ProductCategoryRdoPdo.class, productCategoryJpo))
            .from(productCategoryJpo)
            .where(predicate)
            .orderBy(productCategoryJpo.registeredOn.desc())
            .fetch();

        return subCategoryPdos.stream()
            .map(pdo -> pdo.toRdo(subParentId -> findSubCategories(subParentId, activeOnly)))
            .toList();
    }
}
