package ing.beribtur.facade.api.feature.rnt.item.rest;


import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.item.entity.Product;
import ing.beribtur.aggregate.item.entity.ProductCategory;
import ing.beribtur.aggregate.item.entity.ProductImage;
import ing.beribtur.aggregate.item.entity.ProductVariant;
import ing.beribtur.facade.api.feature.rnt.item.query.FindProductCategoryRntQuery;
import ing.beribtur.facade.api.feature.rnt.item.query.FindProductImageRntQuery;
import ing.beribtur.facade.api.feature.rnt.item.query.FindProductRntQuery;
import ing.beribtur.facade.api.feature.rnt.item.query.FindProductVariantRntQuery;

public interface ItemRntSeekFacade {
    //
    QueryResponse<ProductCategory> findProductCategory(FindProductCategoryRntQuery query);
    QueryResponse<Product> findProduct(FindProductRntQuery query);
    QueryResponse<ProductImage> findProductImage(FindProductImageRntQuery query);
    QueryResponse<ProductVariant> findProductVariant(FindProductVariantRntQuery query);
}