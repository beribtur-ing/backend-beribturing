package ing.beribtur.facade.api.feature.own.item.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.own.item.command.*;

public interface ItemOwnFlowFacade {
    //
    CommandResponse<String> registerProduct(RegisterProductOwnCommand command);
    CommandResponse<String> modifyProduct(ModifyProductOwnCommand command);
    CommandResponse<String> removeProduct(RemoveProductOwnCommand command);

    CommandResponse<String> registerProductImage(RegisterProductImageOwnCommand command);
    CommandResponse<String> modifyProductImage(ModifyProductImageOwnCommand command);
    CommandResponse<String> removeProductImage(RemoveProductImageOwnCommand command);

    CommandResponse<String> registerProductVariant(RegisterProductVariantOwnCommand command);
    CommandResponse<String> modifyProductVariant(ModifyProductVariantOwnCommand command);
    CommandResponse<String> removeProductVariant(RemoveProductVariantOwnCommand command);
}
