package ing.beribtur.facade.api.feature.adm.item.rest;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.aggregate.item.entity.sdo.ProductCategoryCdo;
import ing.beribtur.aggregate.item.entity.sdo.ProductCdo;
import ing.beribtur.aggregate.item.entity.sdo.ProductImageCdo;
import ing.beribtur.aggregate.item.entity.sdo.ProductVariantCdo;
import ing.beribtur.facade.api.feature.adm.item.command.*;
import ing.beribtur.feature.adm.item.flow.ItemAdmFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/admin/item")
public class ItemAdmFlowResource implements ItemAdmFlowFacade {
    //
    private final ItemAdmFlow itemAdmFlow;

    @Override
    @PostMapping("/register-product-category/command")
    public CommandResponse<String> registerProductCategory(@RequestBody RegisterProductCategoryAdmCommand command) {
        //
        command.validate();
        ProductCategoryCdo productCategoryCdo = command.getProductCategoryCdo();
        String entityId = itemAdmFlow.registerProductCategory(productCategoryCdo);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/modify-product-category/command")
    public CommandResponse<String> modifyProductCategory(@RequestBody ModifyProductCategoryAdmCommand command) {
        //
        command.validate();
        String categoryId = command.getCategoryId();
        NameValueList nameValueList = command.getNameValueList();
        String entityId = itemAdmFlow.modifyProductCategory(categoryId, nameValueList);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/remove-product-category/command")
    public CommandResponse<String> removeProductCategory(@RequestBody RemoveProductCategoryAdmCommand command) {
        //
        command.validate();
        String categoryId = command.getCategoryId();
        String entityId = itemAdmFlow.removeProductCategory(categoryId);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/register-product/command")
    public CommandResponse<String> registerProduct(@RequestBody RegisterProductAdmCommand command) {
        //
        command.validate();
        ProductCdo productCdo = command.getProductCdo();
        String entityId = itemAdmFlow.registerProduct(productCdo);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/modify-product/command")
    public CommandResponse<String> modifyProduct(@RequestBody ModifyProductAdmCommand command) {
        //
        command.validate();
        String productId = command.getProductId();
        NameValueList nameValueList = command.getNameValueList();
        String entityId = itemAdmFlow.modifyProduct(productId, nameValueList);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/remove-product/command")
    public CommandResponse<String> removeProduct(@RequestBody RemoveProductAdmCommand command) {
        //
        command.validate();
        String productId = command.getProductId();
        String entityId = itemAdmFlow.removeProduct(productId);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/register-product-image/command")
    public CommandResponse<String> registerProductImage(@RequestBody RegisterProductImageAdmCommand command) {
        //
        command.validate();
        ProductImageCdo productImageCdo = command.getProductImageCdo();
        String entityId = itemAdmFlow.registerProductImage(productImageCdo);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/modify-product-image/command")
    public CommandResponse<String> modifyProductImage(@RequestBody ModifyProductImageAdmCommand command) {
        //
        command.validate();
        String imageId = command.getImageId();
        NameValueList nameValueList = command.getNameValueList();
        String entityId = itemAdmFlow.modifyProductImage(imageId, nameValueList);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/remove-product-image/command")
    public CommandResponse<String> removeProductImage(@RequestBody RemoveProductImageAdmCommand command) {
        //
        command.validate();
        String imageId = command.getImageId();
        String entityId = itemAdmFlow.removeProductImage(imageId);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/register-product-variant/command")
    public CommandResponse<String> registerProductVariant(@RequestBody RegisterProductVariantAdmCommand command) {
        //
        command.validate();
        ProductVariantCdo productVariantCdo = command.getProductVariantCdo();
        String entityId = itemAdmFlow.registerProductVariant(productVariantCdo);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/modify-product-variant/command")
    public CommandResponse<String> modifyProductVariant(@RequestBody ModifyProductVariantAdmCommand command) {
        //
        command.validate();
        String variantId = command.getVariantId();
        NameValueList nameValueList = command.getNameValueList();
        String entityId = itemAdmFlow.modifyProductVariant(variantId, nameValueList);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/remove-product-variant/command")
    public CommandResponse<String> removeProductVariant(@RequestBody RemoveProductVariantAdmCommand command) {
        //
        command.validate();
        String variantId = command.getVariantId();
        String entityId = itemAdmFlow.removeProductVariant(variantId);
        return new CommandResponse<>(entityId);
    }
}
