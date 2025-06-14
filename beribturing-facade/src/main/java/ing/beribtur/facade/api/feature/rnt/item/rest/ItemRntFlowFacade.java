package ing.beribtur.facade.api.feature.rnt.item.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.rnt.item.command.*;

public interface ItemRntFlowFacade {
    //
    CommandResponse<String> registerProductCategory(RegisterProductCategoryRntCommand command);
    CommandResponse<String> modifyProductCategory(ModifyProductCategoryRntCommand command);
    CommandResponse<String> removeProductCategory(RemoveProductCategoryRntCommand command);

    CommandResponse<String> registerProduct(RegisterProductRntCommand command);
    CommandResponse<String> modifyProduct(ModifyProductRntCommand command);
    CommandResponse<String> removeProduct(RemoveProductRntCommand command);

    CommandResponse<String> registerProductImage(RegisterProductImageRntCommand command);
    CommandResponse<String> modifyProductImage(ModifyProductImageRntCommand command);
    CommandResponse<String> removeProductImage(RemoveProductImageRntCommand command);

    CommandResponse<String> registerProductVariant(RegisterProductVariantRntCommand command);
    CommandResponse<String> modifyProductVariant(ModifyProductVariantRntCommand command);
    CommandResponse<String> removeProductVariant(RemoveProductVariantRntCommand command);
}