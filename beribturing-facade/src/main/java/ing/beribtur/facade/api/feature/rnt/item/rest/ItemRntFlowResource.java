package ing.beribtur.facade.api.feature.rnt.item.rest;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.aggregate.item.entity.sdo.ProductCategoryCdo;
import ing.beribtur.aggregate.item.entity.sdo.ProductCdo;
import ing.beribtur.aggregate.item.entity.sdo.ProductImageCdo;
import ing.beribtur.aggregate.item.entity.sdo.ProductVariantCdo;
import ing.beribtur.facade.api.feature.rnt.item.command.*;
import ing.beribtur.feature.rnt.item.flow.ItemRntFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/renter/item")
public class ItemRntFlowResource implements ItemRntFlowFacade {
    //
    private final ItemRntFlow itemRntFlow;

    @Override
    @PostMapping("/register-product-category/command")
    public CommandResponse<String> registerProductCategory(@RequestBody RegisterProductCategoryRntCommand command) {
        //
        command.validate();
        ProductCategoryCdo productCategoryCdo = command.getProductCategoryCdo();
        String entityId = itemRntFlow.registerProductCategory(productCategoryCdo);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/modify-product-category/command")
    public CommandResponse<String> modifyProductCategory(@RequestBody ModifyProductCategoryRntCommand command) {
        //
        command.validate();
        String categoryId = command.getCategoryId();
        NameValueList nameValueList = command.getNameValueList();
        String entityId = itemRntFlow.modifyProductCategory(categoryId, nameValueList);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/remove-product-category/command")
    public CommandResponse<String> removeProductCategory(@RequestBody RemoveProductCategoryRntCommand command) {
        //
        command.validate();
        String categoryId = command.getCategoryId();
        String entityId = itemRntFlow.removeProductCategory(categoryId);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/register-product/command")
    public CommandResponse<String> registerProduct(@RequestBody RegisterProductRntCommand command) {
        //
        command.validate();
        ProductCdo productCdo = command.getProductCdo();
        String entityId = itemRntFlow.registerProduct(productCdo);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/modify-product/command")
    public CommandResponse<String> modifyProduct(@RequestBody ModifyProductRntCommand command) {
        //
        command.validate();
        String productId = command.getProductId();
        NameValueList nameValueList = command.getNameValueList();
        String entityId = itemRntFlow.modifyProduct(productId, nameValueList);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/remove-product/command")
    public CommandResponse<String> removeProduct(@RequestBody RemoveProductRntCommand command) {
        //
        command.validate();
        String productId = command.getProductId();
        String entityId = itemRntFlow.removeProduct(productId);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/register-product-image/command")
    public CommandResponse<String> registerProductImage(@RequestBody RegisterProductImageRntCommand command) {
        //
        command.validate();
        ProductImageCdo productImageCdo = command.getProductImageCdo();
        String entityId = itemRntFlow.registerProductImage(productImageCdo);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/modify-product-image/command")
    public CommandResponse<String> modifyProductImage(@RequestBody ModifyProductImageRntCommand command) {
        //
        command.validate();
        String imageId = command.getImageId();
        NameValueList nameValueList = command.getNameValueList();
        String entityId = itemRntFlow.modifyProductImage(imageId, nameValueList);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/remove-product-image/command")
    public CommandResponse<String> removeProductImage(@RequestBody RemoveProductImageRntCommand command) {
        //
        command.validate();
        String imageId = command.getImageId();
        String entityId = itemRntFlow.removeProductImage(imageId);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/register-product-variant/command")
    public CommandResponse<String> registerProductVariant(@RequestBody RegisterProductVariantRntCommand command) {
        //
        command.validate();
        ProductVariantCdo productVariantCdo = command.getProductVariantCdo();
        String entityId = itemRntFlow.registerProductVariant(productVariantCdo);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/modify-product-variant/command")
    public CommandResponse<String> modifyProductVariant(@RequestBody ModifyProductVariantRntCommand command) {
        //
        command.validate();
        String variantId = command.getVariantId();
        NameValueList nameValueList = command.getNameValueList();
        String entityId = itemRntFlow.modifyProductVariant(variantId, nameValueList);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/remove-product-variant/command")
    public CommandResponse<String> removeProductVariant(@RequestBody RemoveProductVariantRntCommand command) {
        //
        command.validate();
        String variantId = command.getVariantId();
        String entityId = itemRntFlow.removeProductVariant(variantId);
        return new CommandResponse<>(entityId);
    }
}