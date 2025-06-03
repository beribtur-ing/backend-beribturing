package ing.beribtur.facade.api.feature.adm.item.rest;


import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.item.entity.Product;
import ing.beribtur.aggregate.item.entity.ProductCategory;
import ing.beribtur.aggregate.item.entity.ProductImage;
import ing.beribtur.aggregate.item.entity.ProductVariant;
import ing.beribtur.facade.api.feature.adm.item.query.FindProductCategoryAdmQuery;
import ing.beribtur.facade.api.feature.adm.item.query.FindProductImageAdmQuery;
import ing.beribtur.facade.api.feature.adm.item.query.FindProductAdmQuery;
import ing.beribtur.facade.api.feature.adm.item.query.FindProductVariantAdmQuery;

public interface ItemAdmSeekFacade {
    //
    QueryResponse<ProductCategory> findProductCategory(FindProductCategoryAdmQuery query);
    QueryResponse<Product> findProduct(FindProductAdmQuery query);
    QueryResponse<ProductImage> findProductImage(FindProductImageAdmQuery query);
    QueryResponse<ProductVariant> findProductVariant(FindProductVariantAdmQuery query);
}
