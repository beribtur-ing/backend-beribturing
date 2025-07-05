package ing.beribtur.facade.api.feature.adm.lender.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.adm.lender.command.ModifyLenderStatusCommand;

public interface LenderAdmFlowFacade {
    //
    CommandResponse<String> modifyLenderStatus(ModifyLenderStatusCommand command);
}
