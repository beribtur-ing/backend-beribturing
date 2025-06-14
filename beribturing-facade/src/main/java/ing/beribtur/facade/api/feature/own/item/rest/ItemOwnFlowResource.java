package ing.beribtur.facade.api.feature.own.item.rest;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.own.item.command.*;
import ing.beribtur.feature.own.item.flow.ItemOwnFlow;
import ing.beribtur.feature.shared.sdo.ProductVariantRegCdo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/owner/item")
public class ItemOwnFlowResource implements ItemOwnFlowFacade {
    //
    private final ItemOwnFlow itemOwnFlow;

    @Override
    @PostMapping("/register-product/command")
    public CommandResponse<String> registerProduct(@RequestBody RegisterProductOwnCommand command) {
        //
        command.validate();
        String entityId = itemOwnFlow.registerProduct(command.getProductOwnRegCdo());
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/modify-product/command")
    public CommandResponse<String> modifyProduct(@RequestBody ModifyProductOwnCommand command) {
        //
        command.validate();
        String productId = command.getProductId();
        NameValueList nameValueList = command.getNameValueList();
        String entityId = itemOwnFlow.modifyProduct(productId, nameValueList);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/remove-product/command")
    public CommandResponse<String> removeProduct(@RequestBody RemoveProductOwnCommand command) {
        //
        command.validate();
        String productId = command.getProductId();
        String entityId = itemOwnFlow.removeProduct(productId);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/modify-product-image/command")
    public CommandResponse<String> modifyProductImage(@RequestBody ModifyProductImageOwnCommand command) {
        //
        command.validate();
        String imageId = command.getImageId();
        NameValueList nameValueList = command.getNameValueList();
        String entityId = itemOwnFlow.modifyProductImage(imageId, nameValueList);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/remove-product-image/command")
    public CommandResponse<String> removeProductImage(@RequestBody RemoveProductImageOwnCommand command) {
        //
        command.validate();
        String imageId = command.getImageId();
        String entityId = itemOwnFlow.removeProductImage(imageId);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping(value = "/register-product-variant/command", consumes = "multipart/form-data")
    public CommandResponse<String> registerProductVariant(@RequestPart("command") RegisterProductVariantOwnCommand command, @RequestPart(value = "images", required = false) List<MultipartFile> images) throws Exception {
        //
        command.validate();
        ProductVariantRegCdo productVariantRegCdo = command.getProductVariantRegCdo();
        String entityId = itemOwnFlow.registerProductVariant(productVariantRegCdo, images);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/modify-product-variant/command")
    public CommandResponse<String> modifyProductVariant(@RequestBody ModifyProductVariantOwnCommand command) {
        //
        command.validate();
        String variantId = command.getVariantId();
        NameValueList nameValueList = command.getNameValueList();
        String entityId = itemOwnFlow.modifyProductVariant(variantId, nameValueList);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/remove-product-variant/command")
    public CommandResponse<String> removeProductVariant(@RequestBody RemoveProductVariantOwnCommand command) {
        //
        command.validate();
        String variantId = command.getVariantId();
        String entityId = itemOwnFlow.removeProductVariant(variantId);
        return new CommandResponse<>(entityId);
    }
}
