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
import ing.beribtur.feature.own.item.seek.ItemOwnSeek;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/owner/item")
public class ItemOwnSeekResource implements ItemOwnSeekFacade {
    //
    private final ItemOwnSeek itemOwnSeek;

    @Override
    @PostMapping("/find-product-category/query")
    public QueryResponse<ProductCategory> findProductCategory(@RequestBody FindProductCategoryOwnQuery query) {
        //
        query.validate();
        String categoryId = query.getCategoryId();
        ProductCategory response = itemOwnSeek.findProductCategoryById(categoryId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-product/query")
    public QueryResponse<Product> findProduct(@RequestBody FindProductOwnQuery query) {
        //
        query.validate();
        String productId = query.getProductId();
        Product response = itemOwnSeek.findProductById(productId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-product-image/query")
    public QueryResponse<ProductImage> findProductImage(@RequestBody FindProductImageOwnQuery query) {
        //
        query.validate();
        String imageId = query.getImageId();
        ProductImage response = itemOwnSeek.findProductImageById(imageId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-product-variant/query")
    public QueryResponse<ProductVariant> findProductVariant(@RequestBody FindProductVariantOwnQuery query) {
        //
        query.validate();
        String variantId = query.getVariantId();
        ProductVariant response = itemOwnSeek.findProductVariantById(variantId);
        return new QueryResponse<>(response);
    }
}