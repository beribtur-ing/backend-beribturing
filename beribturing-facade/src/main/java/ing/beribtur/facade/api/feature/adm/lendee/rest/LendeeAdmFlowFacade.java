package ing.beribtur.facade.api.feature.adm.lendee.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.adm.lendee.command.ModifyLendeeStatusCommand;

public interface LendeeAdmFlowFacade {
    //
    CommandResponse<String> modifyLendeeStatus(ModifyLendeeStatusCommand command);
}
