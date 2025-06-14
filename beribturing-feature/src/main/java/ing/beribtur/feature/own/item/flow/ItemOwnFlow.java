package ing.beribtur.feature.own.item.flow;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.item.entity.sdo.ProductCdo;
import ing.beribtur.aggregate.item.entity.sdo.ProductImageCdo;
import ing.beribtur.aggregate.item.entity.sdo.ProductVariantCdo;
import ing.beribtur.aggregate.item.logic.ProductImageLogic;
import ing.beribtur.aggregate.item.logic.ProductLogic;
import ing.beribtur.aggregate.item.logic.ProductVariantLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemOwnFlow {
    //
    private final ProductLogic productLogic;
    private final ProductVariantLogic productVariantLogic;
    private final ProductImageLogic productImageLogic;

    public String registerProduct(ProductCdo productCdo) {
        //
        return productLogic.registerProduct(productCdo);
    }

    public String modifyProduct(String productId, NameValueList nameValueList) {
        //
        productLogic.modifyProduct(productId, nameValueList);
        return productId;
    }

    public String removeProduct(String productId) {
        //
        productLogic.removeProduct(productId);
        return productId;
    }

    public String registerProductImage(ProductImageCdo productImageCdo) {
        //
        return productImageLogic.registerProductImage(productImageCdo);
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

    public String registerProductVariant(ProductVariantCdo productVariantCdo) {
        //
        return productVariantLogic.registerProductVariant(productVariantCdo);
    }

    public String modifyProductVariant(String productVariantId, NameValueList nameValueList) {
        //
        productVariantLogic.modifyProductVariant(productVariantId, nameValueList);
        return productVariantId;
    }

    public String removeProductVariant(String productVariantId) {
        //
        productVariantLogic.removeProductVariant(productVariantId);
        return productVariantId;
    }
}
