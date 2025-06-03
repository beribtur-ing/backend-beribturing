package ing.beribtur.facade.api.feature.item.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.item.entity.Product;
import ing.beribtur.aggregate.item.entity.ProductCategory;
import ing.beribtur.aggregate.item.entity.ProductImage;
import ing.beribtur.aggregate.item.entity.ProductVariant;
import ing.beribtur.facade.api.feature.item.query.*;
import ing.beribtur.feature.item.seek.ItemSeek;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/item")
public class ItemSeekResource implements ItemSeekFacade {
    //
    private final ItemSeek itemSeek;

    @Override
    @PostMapping("/find-bulletin-board/query")
    public QueryResponse<Board> findBulletinBoard(@RequestBody FindBulletinBoardQuery query) {
        //
        query.validate();
        String boardId = query.getBoardId();
        Board response = bulletinBoardSeek.findBulletinBoard(boardId);
        query.setResponse(response);
        return query.getResponse();
    }

    @Override
    @PostMapping("/find-product-category/query")
    public QueryResponse<ProductCategory> findProductCategory(@RequestBody FindProductCategoryQuery query) {
        //
        query.validate();
        String categoryId = query.getCategoryId();
        ProductCategory response = itemSeek.findProductCategoryById(categoryId);
        return new QueryResponse<ProductCategory>(response);
    }

    @Override
    @PostMapping("/find-product/query")
    public QueryResponse<Product> findProduct(@RequestBody FindProductQuery query) {
        //
        query.validate();
        String productId = query.getProductId();
        Product response = itemSeek.findProductById(productId);
        return new QueryResponse<Product>(response);
    }

    @Override
    @PostMapping("/find-product-image/query")
    public QueryResponse<ProductImage> findProductImage(@RequestBody FindProductImageQuery query) {
        //
        query.validate();
        String imageId = query.getImageId();
        ProductImage response = itemSeek.findProductImageById(imageId);
        return new QueryResponse<ProductImage>(response);
    }

    @Override
    @PostMapping("/find-product-variant/query")
    public QueryResponse<ProductVariant> findProductVariant(@RequestBody FindProductVariantQuery query) {
        //
        query.validate();
        String variantId = query.getVariantId();
        ProductVariant response = itemSeek.findProductVariantById(variantId);
        return new QueryResponse<ProductVariant>(response);
    }
}
