package ing.beribtur.facade.api.feature.item.rest;


import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.item.entity.Product;
import ing.beribtur.aggregate.item.entity.ProductCategory;
import ing.beribtur.aggregate.item.entity.ProductImage;
import ing.beribtur.aggregate.item.entity.ProductVariant;
import ing.beribtur.facade.api.feature.item.query.*;

public interface ItemSeekFacade {
    //
    QueryResponse<ProductCategory> findProductCategory(FindProductCategoryQuery query);
    QueryResponse<Product> findProduct(FindProductQuery query);
    QueryResponse<ProductImage> findProductImage(FindProductImageQuery query);
    QueryResponse<ProductVariant> findProductVariant(FindProductVariantQuery query);
}
