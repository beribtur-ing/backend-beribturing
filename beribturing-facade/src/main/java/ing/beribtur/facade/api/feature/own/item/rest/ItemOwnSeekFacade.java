package ing.beribtur.facade.api.feature.own.item.rest;


import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.item.entity.Product;
import ing.beribtur.aggregate.item.entity.ProductCategory;
import ing.beribtur.aggregate.item.entity.ProductImage;
import ing.beribtur.aggregate.item.entity.ProductVariant;
import ing.beribtur.facade.api.feature.own.item.query.FindProductCategoryOwnQuery;
import ing.beribtur.facade.api.feature.own.item.query.FindProductImageOwnQuery;
import ing.beribtur.facade.api.feature.own.item.query.FindProductOwnQuery;
import ing.beribtur.facade.api.feature.own.item.query.FindProductVariantOwnQuery;

public interface ItemOwnSeekFacade {
    //
    QueryResponse<ProductCategory> findProductCategory(FindProductCategoryOwnQuery query);
    QueryResponse<Product> findProduct(FindProductOwnQuery query);
    QueryResponse<ProductImage> findProductImage(FindProductImageOwnQuery query);
    QueryResponse<ProductVariant> findProductVariant(FindProductVariantOwnQuery query);
}