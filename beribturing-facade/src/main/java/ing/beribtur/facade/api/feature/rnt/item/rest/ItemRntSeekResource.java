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
import ing.beribtur.feature.rnt.item.seek.ItemRntSeek;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/renter/item")
public class ItemRntSeekResource implements ItemRntSeekFacade {
    //
    private final ItemRntSeek itemRntSeek;

    @Override
    @PostMapping("/find-product-category/query")
    public QueryResponse<ProductCategory> findProductCategory(@RequestBody FindProductCategoryRntQuery query) {
        //
        query.validate();
        String categoryId = query.getCategoryId();
        ProductCategory response = itemRntSeek.findProductCategoryById(categoryId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-product/query")
    public QueryResponse<Product> findProduct(@RequestBody FindProductRntQuery query) {
        //
        query.validate();
        String productId = query.getProductId();
        Product response = itemRntSeek.findProductById(productId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-product-image/query")
    public QueryResponse<ProductImage> findProductImage(@RequestBody FindProductImageRntQuery query) {
        //
        query.validate();
        String imageId = query.getImageId();
        ProductImage response = itemRntSeek.findProductImageById(imageId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-product-variant/query")
    public QueryResponse<ProductVariant> findProductVariant(@RequestBody FindProductVariantRntQuery query) {
        //
        query.validate();
        String variantId = query.getVariantId();
        ProductVariant response = itemRntSeek.findProductVariantById(variantId);
        return new QueryResponse<>(response);
    }
}