package ing.beribtur.feature.rnt.item.seek;

import ing.beribtur.aggregate.item.entity.Product;
import ing.beribtur.aggregate.item.entity.ProductCategory;
import ing.beribtur.aggregate.item.entity.ProductImage;
import ing.beribtur.aggregate.item.entity.ProductVariant;
import ing.beribtur.aggregate.item.logic.ProductCategoryLogic;
import ing.beribtur.aggregate.item.logic.ProductImageLogic;
import ing.beribtur.aggregate.item.logic.ProductLogic;
import ing.beribtur.aggregate.item.logic.ProductVariantLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemRntSeek {
    //
    private final ProductCategoryLogic productCategoryLogic;
    private final ProductLogic productLogic;
    private final ProductVariantLogic productVariantLogic;
    private final ProductImageLogic productImageLogic;

    public List<ProductCategory> findProductCategoriesByParentId(String parentId) {
        //
        return productCategoryLogic.findProductCategoriesByParentId(parentId);
    }

    public ProductCategory findProductCategoryById(String id) {
        //
        return productCategoryLogic.findProductCategory(id);
    }

    public List<Product> findProductsByOwnerId(String ownerId) {
        //
        return productLogic.findByOwnerId(ownerId);
    }

    public List<Product> findProductsByCategoryId(String categoryId) {
        //
        return productLogic.findByCategoryId(categoryId);
    }

    public Product findProductById(String id) {
        //
        return productLogic.findProduct(id);
    }

    public List<ProductVariant> findProductVariantsByProductId(String productId) {
        //
        return productVariantLogic.findProductVariantsByProductId(productId);
    }

    public ProductVariant findProductVariantById(String id) {
        //
        return productVariantLogic.findProductVariant(id);
    }

    public List<ProductImage> findProductImagesByVariantId(String productId) {
        //
        return productImageLogic.findProductImagesByVariantId(productId);
    }

    public ProductImage findProductImageById(String id) {
        //
        return productImageLogic.findProductImage(id);
    }
}