package ing.beribtur.facade.api.feature.adm.item.rest;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.adm.item.command.*;
import ing.beribtur.feature.adm.item.flow.ItemAdmFlow;
import ing.beribtur.feature.shared.sdo.ProductVariantRegCdo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
        String entityId = itemAdmFlow.registerProductCategory(command.getProductCategoryRegCdo());
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
        String entityId = itemAdmFlow.registerProduct(command.getProductAdmRegCdo());
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
    public CommandResponse<String> registerProductVariant(@RequestPart("command") RegisterProductVariantAdmCommand command, @RequestPart(value = "images", required = false) List<MultipartFile> images) throws Exception {
        //
        command.validate();
        ProductVariantRegCdo productVariantRegCdo = command.getProductVariantRegCdo();
        String entityId = itemAdmFlow.registerProductVariant(productVariantRegCdo, images);
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
