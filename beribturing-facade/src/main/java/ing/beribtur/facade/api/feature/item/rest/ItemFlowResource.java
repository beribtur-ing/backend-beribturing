package ing.beribtur.facade.api.feature.item.rest;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.aggregate.item.entity.sdo.ProductCategoryCdo;
import ing.beribtur.aggregate.item.entity.sdo.ProductCdo;
import ing.beribtur.aggregate.item.entity.sdo.ProductImageCdo;
import ing.beribtur.aggregate.item.entity.sdo.ProductVariantCdo;
import ing.beribtur.facade.api.feature.item.command.*;
import ing.beribtur.feature.item.flow.ItemFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/item")
public class ItemFlowResource implements ItemFlowFacade {
    //
    private final ItemFlow itemFlow;

    @Override
    @PostMapping("/register-product-category/command")
    public CommandResponse<String> registerProductCategory(@RequestBody RegisterProductCategoryCommand command) {
        //
        command.validate();
        ProductCategoryCdo productCategoryCdo = command.getProductCategoryCdo();
        String entityId = itemFlow.registerProductCategory(productCategoryCdo);
        return new CommandResponse<String>(entityId);
    }

    @Override
    @PostMapping("/modify-product-category/command")
    public CommandResponse<String> modifyProductCategory(@RequestBody ModifyProductCategoryCommand command) {
        //
        command.validate();
        String categoryId = command.getCategoryId();
        NameValueList nameValueList = command.getNameValueList();
        String entityId = itemFlow.modifyProductCategory(categoryId, nameValueList);
        return new CommandResponse<String>(entityId);
    }

    @Override
    @PostMapping("/remove-product-category/command")
    public CommandResponse<String> removeProductCategory(@RequestBody RemoveProductCategoryCommand command) {
        //
        command.validate();
        String categoryId = command.getCategoryId();
        String entityId = itemFlow.removeProductCategory(categoryId);
        return new CommandResponse<String>(entityId);
    }

    @Override
    @PostMapping("/register-product/command")
    public CommandResponse<String> registerProduct(@RequestBody RegisterProductCommand command) {
        //
        command.validate();
        ProductCdo productCdo = command.getProductCdo();
        String entityId = itemFlow.registerProduct(productCdo);
        return new CommandResponse<String>(entityId);
    }

    @Override
    @PostMapping("/modify-product/command")
    public CommandResponse<String> modifyProduct(@RequestBody ModifyProductCommand command) {
        //
        command.validate();
        String productId = command.getProductId();
        NameValueList nameValueList = command.getNameValueList();
        String entityId = itemFlow.modifyProduct(productId, nameValueList);
        return new CommandResponse<String>(entityId);
    }

    @Override
    @PostMapping("/remove-product/command")
    public CommandResponse<String> removeProduct(@RequestBody RemoveProductCommand command) {
        //
        command.validate();
        String productId = command.getProductId();
        String entityId = itemFlow.removeProduct(productId);
        return new CommandResponse<String>(entityId);
    }

    @Override
    @PostMapping("/register-product-image/command")
    public CommandResponse<String> registerProductImage(@RequestBody RegisterProductImageCommand command) {
        //
        command.validate();
        ProductImageCdo productImageCdo = command.getProductImageCdo();
        String entityId = itemFlow.registerProductImage(productImageCdo);
        return new CommandResponse<String>(entityId);
    }

    @Override
    @PostMapping("/modify-product-image/command")
    public CommandResponse<String> modifyProductImage(@RequestBody ModifyProductImageCommand command) {
        //
        command.validate();
        String imageId = command.getImageId();
        NameValueList nameValueList = command.getNameValueList();
        String entityId = itemFlow.modifyProductImage(imageId, nameValueList);
        return new CommandResponse<String>(entityId);
    }

    @Override
    @PostMapping("/remove-product-image/command")
    public CommandResponse<String> removeProductImage(@RequestBody RemoveProductImageCommand command) {
        //
        command.validate();
        String imageId = command.getImageId();
        String entityId = itemFlow.removeProductImage(imageId);
        return new CommandResponse<String>(entityId);
    }

    @Override
    @PostMapping("/register-product-variant/command")
    public CommandResponse<String> registerProductVariant(@RequestBody RegisterProductVariantCommand command) {
        //
        command.validate();
        ProductVariantCdo productVariantCdo = command.getProductVariantCdo();
        String entityId = itemFlow.registerProductVariant(productVariantCdo);
        return new CommandResponse<String>(entityId);
    }

    @Override
    @PostMapping("/modify-product-variant/command")
    public CommandResponse<String> modifyProductVariant(@RequestBody ModifyProductVariantCommand command) {
        //
        command.validate();
        String variantId = command.getVariantId();
        NameValueList nameValueList = command.getNameValueList();
        String entityId = itemFlow.modifyProductVariant(variantId, nameValueList);
        return new CommandResponse<String>(entityId);
    }

    @Override
    @PostMapping("/remove-product-variant/command")
    public CommandResponse<String> removeProductVariant(@RequestBody RemoveProductVariantCommand command) {
        //
        command.validate();
        String variantId = command.getVariantId();
        String entityId = itemFlow.removeProductVariant(variantId);
        return new CommandResponse<String>(entityId);
    }
}
