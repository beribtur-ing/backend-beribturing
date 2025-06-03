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
import ing.beribtur.feature.adm.item.seek.ItemAdmSeek;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/admin/item")
public class ItemAdmSeekResource implements ItemAdmSeekFacade {
    //
    private final ItemAdmSeek itemAdmSeek;

    @Override
    @PostMapping("/find-product-category/query")
    public QueryResponse<ProductCategory> findProductCategory(@RequestBody FindProductCategoryAdmQuery query) {
        //
        query.validate();
        String categoryId = query.getCategoryId();
        ProductCategory response = itemAdmSeek.findProductCategoryById(categoryId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-product/query")
    public QueryResponse<Product> findProduct(@RequestBody FindProductAdmQuery query) {
        //
        query.validate();
        String productId = query.getProductId();
        Product response = itemAdmSeek.findProductById(productId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-product-image/query")
    public QueryResponse<ProductImage> findProductImage(@RequestBody FindProductImageAdmQuery query) {
        //
        query.validate();
        String imageId = query.getImageId();
        ProductImage response = itemAdmSeek.findProductImageById(imageId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-product-variant/query")
    public QueryResponse<ProductVariant> findProductVariant(@RequestBody FindProductVariantAdmQuery query) {
        //
        query.validate();
        String variantId = query.getVariantId();
        ProductVariant response = itemAdmSeek.findProductVariantById(variantId);
        return new QueryResponse<>(response);
    }
}
