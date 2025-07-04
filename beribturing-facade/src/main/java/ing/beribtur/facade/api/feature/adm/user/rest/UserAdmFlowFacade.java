package ing.beribtur.facade.api.feature.adm.user.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.adm.user.command.ChangePasswordAdmCommand;

public interface UserAdmFlowFacade {
    CommandResponse<String> changePassword(ChangePasswordAdmCommand command) throws Exception;
}