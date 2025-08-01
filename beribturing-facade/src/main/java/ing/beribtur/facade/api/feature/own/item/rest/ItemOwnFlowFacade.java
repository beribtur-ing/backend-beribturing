package ing.beribtur.facade.api.feature.own.item.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.own.item.command.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemOwnFlowFacade {
    //
    CommandResponse<String> registerProduct(RegisterProductOwnCommand command);
    CommandResponse<String> modifyProduct(ModifyProductOwnCommand command);
    CommandResponse<String> removeProduct(RemoveProductOwnCommand command);

    CommandResponse<String> modifyProductImage(ModifyProductImageOwnCommand command);
    CommandResponse<String> removeProductImage(RemoveProductImageOwnCommand command);

    CommandResponse<String> registerProductVariant(RegisterProductVariantOwnCommand command, List<MultipartFile> images) throws Exception;
    CommandResponse<String> modifyProductVariant(ModifyProductVariantOwnCommand command);
    CommandResponse<String> removeProductVariant(RemoveProductVariantOwnCommand command);
}
