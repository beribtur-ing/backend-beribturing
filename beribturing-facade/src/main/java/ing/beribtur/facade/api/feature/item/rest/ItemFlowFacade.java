package ing.beribtur.facade.api.feature.item.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.item.command.*;

public interface ItemFlowFacade {
    //
    CommandResponse<String> registerProductCategory(RegisterProductCategoryCommand command);
    CommandResponse<String> modifyProductCategory(ModifyProductCategoryCommand command);
    CommandResponse<String> removeProductCategory(RemoveProductCategoryCommand command);

    CommandResponse<String> registerProduct(RegisterProductCommand command);
    CommandResponse<String> modifyProduct(ModifyProductCommand command);
    CommandResponse<String> removeProduct(RemoveProductCommand command);

    CommandResponse<String> registerProductImage(RegisterProductImageCommand command);
    CommandResponse<String> modifyProductImage(ModifyProductImageCommand command);
    CommandResponse<String> removeProductImage(RemoveProductImageCommand command);

    CommandResponse<String> registerProductVariant(RegisterProductVariantCommand command);
    CommandResponse<String> modifyProductVariant(ModifyProductVariantCommand command);
    CommandResponse<String> removeProductVariant(RemoveProductVariantCommand command);
}
