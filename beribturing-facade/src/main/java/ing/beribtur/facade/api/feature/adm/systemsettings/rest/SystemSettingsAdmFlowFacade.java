package ing.beribtur.facade.api.feature.adm.systemsettings.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.adm.systemsettings.command.ModifySystemSettingsAdmCommand;
import ing.beribtur.facade.api.feature.adm.systemsettings.command.RegisterSystemSettingsAdmCommand;

public interface SystemSettingsAdmFlowFacade {
    //
    CommandResponse<String> registerSystemSettings(RegisterSystemSettingsAdmCommand command);

    CommandResponse<String> modifySystemSettings(ModifySystemSettingsAdmCommand command);
}