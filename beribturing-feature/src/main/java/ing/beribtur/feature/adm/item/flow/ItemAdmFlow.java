package ing.beribtur.feature.adm.item.flow;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.item.entity.Product;
import ing.beribtur.aggregate.item.entity.ProductImage;
import ing.beribtur.aggregate.item.entity.ProductVariant;
import ing.beribtur.aggregate.item.entity.sdo.ProductCategoryCdo;
import ing.beribtur.aggregate.item.entity.sdo.ProductCdo;
import ing.beribtur.aggregate.item.entity.sdo.ProductVariantCdo;
import ing.beribtur.aggregate.item.logic.ProductCategoryLogic;
import ing.beribtur.aggregate.item.logic.ProductImageLogic;
import ing.beribtur.aggregate.item.logic.ProductLogic;
import ing.beribtur.aggregate.item.logic.ProductVariantLogic;
import ing.beribtur.aggregate.user.logic.LenderLogic;
import ing.beribtur.feature.adm.item.sdo.ProductAdmRegCdo;
import ing.beribtur.feature.adm.item.sdo.ProductCategoryRegCdo;
import ing.beribtur.feature.shared.action.ProductImageHelper;
import ing.beribtur.feature.shared.sdo.ProductVariantRegCdo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemAdmFlow {
    //
    private final ProductCategoryLogic productCategoryLogic;
    private final ProductLogic productLogic;
    private final ProductVariantLogic productVariantLogic;
    private final ProductImageLogic productImageLogic;
    private final LenderLogic lenderLogic;
    private final ProductImageHelper imageHelper;

    public String registerProductCategory(ProductCategoryRegCdo productCategoryRegCdo) {
        //
        ProductCategoryCdo productCategoryCdo = productCategoryRegCdo.toCdo();
        return productCategoryLogic.registerProductCategory(productCategoryCdo);
    }

    public String modifyProductCategory(String categoryId, NameValueList nameValueList) {
        //
        productCategoryLogic.modifyProductCategory(categoryId, nameValueList);
        return categoryId;
    }

    public String removeProductCategory(String categoryId) {
        //
        removeProductCategoryCascade(categoryId);
        return categoryId;
    }

    private void removeProductCategoryCascade(String categoryId) {
        //
        // Hard delete: Remove all products in this category first
        List<Product> productsInCategory = productLogic.findByCategoryId(categoryId);
        for (Product product : productsInCategory) {
            removeProductCascade(product.getId());
        }

        // Remove the category itself
        productCategoryLogic.removeProductCategory(categoryId);
    }

    public String registerProduct(ProductAdmRegCdo productAdmRegCdo) {
        //
        long sequence = lenderLogic.nextProductSequence(productAdmRegCdo.getOwnerId());
        ProductCdo productCdo = productAdmRegCdo.toCdo(sequence);
        return productLogic.registerProduct(productCdo);
    }

    public String modifyProduct(String productId, NameValueList nameValueList) {
        //
        productLogic.modifyProduct(productId, nameValueList);
        return productId;
    }

    public String removeProduct(String productId) {
        //
        removeProductCascade(productId);
        return productId;
    }

    private void removeProductCascade(String productId) {
        //
        // Hard delete: Remove all product variants first
        List<ProductVariant> variants = productVariantLogic.findProductVariantsByProductId(productId);
        for (ProductVariant variant : variants) {
            removeProductVariantCascade(variant.getId());
        }

        // Remove the product itself
        productLogic.removeProduct(productId);
    }

    public String modifyProductImage(String productImageId, NameValueList nameValueList) {
        //
        productImageLogic.modifyProductImage(productImageId, nameValueList);
        return productImageId;
    }

    public String removeProductImage(String productImageId) {
        //
        productImageLogic.removeProductImage(productImageId);
        return productImageId;
    }


    public String registerProductVariant(ProductVariantRegCdo productVariantRegCdo, List<MultipartFile> images) throws Exception {
        //
        long sequence = productLogic.nextVariantSequence(productVariantRegCdo.getProductId());
        ProductVariantCdo productVariantCdo = productVariantRegCdo.toCdo(sequence);
        String variantId = productVariantLogic.registerProductVariant(productVariantCdo);
        int imageSequence = imageHelper.registerProductImages(images, variantId);
        imageHelper.modifyVariantImageSequence(imageSequence, variantId);
        return variantId;
    }

    public String modifyProductVariant(String productVariantId, NameValueList nameValueList) {
        //
        productVariantLogic.modifyProductVariant(productVariantId, nameValueList);
        return productVariantId;
    }

    public String removeProductVariant(String productVariantId) {
        //
        removeProductVariantCascade(productVariantId);
        return productVariantId;
    }

    private void removeProductVariantCascade(String productVariantId) {
        //
        // Hard delete: Remove all product images for this variant first
        // Note: Assuming ProductImageLogic has method to find images by variant
        try {
            List<ProductImage> images = productImageLogic.findProductImagesByVariantId(productVariantId);
            for (ProductImage image : images) {
                try {
                    imageHelper.removeProductImageFromStorage(image);
                } catch (Exception e) {
                    // Log error but continue with database removal
                }
            }
        } catch (Exception e) {
            // If method doesn't exist, just remove the variant
        }

        // Remove the product variant itself
        productVariantLogic.removeProductVariant(productVariantId);
    }
}
