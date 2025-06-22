package ing.beribtur.facade.api.feature.adm.payment.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.adm.payment.command.*;

import java.util.List;

public interface DiscountAdmFlowFacade {
    // Create
    CommandResponse<String> registerDiscount(RegisterDiscountAdmCommand command);
    CommandResponse<List<String>> registerDiscounts(List<RegisterDiscountAdmCommand> commands);
    
    // Update
    CommandResponse<Void> modifyDiscount(ModifyDiscountAdmCommand command);
    CommandResponse<Void> activateDiscount(ActivateDiscountAdmCommand command);
    CommandResponse<Void> deactivateDiscount(DeactivateDiscountAdmCommand command);
    
    // Delete
    CommandResponse<Void> removeDiscount(RemoveDiscountAdmCommand command);
}
