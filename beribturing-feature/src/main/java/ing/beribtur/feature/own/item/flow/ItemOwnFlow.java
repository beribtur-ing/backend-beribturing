package ing.beribtur.feature.own.item.flow;

import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.item.entity.ProductImage;
import ing.beribtur.aggregate.item.entity.ProductVariant;
import ing.beribtur.aggregate.item.entity.sdo.ProductCdo;
import ing.beribtur.aggregate.item.entity.sdo.ProductVariantCdo;
import ing.beribtur.aggregate.item.logic.ProductImageLogic;
import ing.beribtur.aggregate.item.logic.ProductLogic;
import ing.beribtur.aggregate.item.logic.ProductVariantLogic;
import ing.beribtur.aggregate.user.logic.LenderLogic;
import ing.beribtur.feature.own.item.sdo.ProductOwnRegCdo;
import ing.beribtur.feature.shared.action.AuthHelper;
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
public class ItemOwnFlow {
    //
    private final ProductLogic productLogic;
    private final ProductVariantLogic productVariantLogic;
    private final ProductImageLogic productImageLogic;
    private final LenderLogic lenderLogic;
    private final AuthHelper authHelper;
    private final ProductImageHelper imageHelper;

    public String registerProduct(ProductOwnRegCdo productOwnRegCdo) {
        //
        String ownerId = authHelper.currentUserId();
        long sequence = lenderLogic.nextProductSequence(ownerId);
        ProductCdo productCdo = productOwnRegCdo.toCdo(ownerId, sequence);
        return productLogic.registerProduct(productCdo);
    }

    public String modifyProduct(String productId, NameValueList nameValueList) {
        //
        productLogic.modifyProduct(productId, nameValueList);
        return productId;
    }

    public String removeProduct(String productId) {
        //
        removeProductCascadeSoft(productId);
        return productId;
    }

    private void removeProductCascadeSoft(String productId) {
        //
        // Soft delete: Mark all product variants as inactive first
        List<ProductVariant> variants = productVariantLogic.findProductVariantsByProductId(productId);
        for (ProductVariant variant : variants) {
            removeProductVariantCascadeSoft(variant.getId());
        }

        // Soft delete: Mark product as inactive
        NameValueList nameValues = NameValueList.newInstance();
        nameValues.add(NameValue.of("active", "false"));
        productLogic.modifyProduct(productId, nameValues);
    }

    public String modifyProductImage(String productImageId, NameValueList nameValueList) {
        //
        productImageLogic.modifyProductImage(productImageId, nameValueList);
        return productImageId;
    }

    public String removeProductImage(String productImageId) {
        //
        // Soft delete image record in database
        NameValueList imageNameValues = NameValueList.newInstance();
        imageNameValues.add(NameValue.of("active", "false"));
        productImageLogic.modifyProductImage(productImageId, imageNameValues);
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
        removeProductVariantCascadeSoft(productVariantId);
        return productVariantId;
    }

    private void removeProductVariantCascadeSoft(String productVariantId) {
        //
        // Soft delete: Mark product images as inactive and handle MinIO files
        try {
            List<ProductImage> images = productImageLogic.findProductImagesByVariantId(productVariantId);
            for (ProductImage image : images) {
                removeProductImage(image.getId());
            }
        } catch (Exception e) {
            // If method doesn't exist or images don't have active field, skip image soft delete
        }

        // Soft delete: Mark product variant as inactive instead of deleting
        NameValueList nameValues = NameValueList.newInstance();
        nameValues.add(NameValue.of("active", "false"));
        productVariantLogic.modifyProductVariant(productVariantId, nameValues);
    }
}
