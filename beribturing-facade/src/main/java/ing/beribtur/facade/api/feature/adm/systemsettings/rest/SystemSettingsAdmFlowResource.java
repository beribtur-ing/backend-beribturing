package ing.beribtur.facade.api.feature.adm.systemsettings.rest;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.aggregate.account.entity.sdo.SystemSettingsCdo;
import ing.beribtur.facade.api.feature.adm.systemsettings.command.ModifySystemSettingsAdmCommand;
import ing.beribtur.facade.api.feature.adm.systemsettings.command.RegisterSystemSettingsAdmCommand;
import ing.beribtur.feature.adm.systemsettings.flow.SystemSettingsAdmFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feature/admin/system-settings")
@RequiredArgsConstructor
public class SystemSettingsAdmFlowResource implements SystemSettingsAdmFlowFacade {
    //
    private final SystemSettingsAdmFlow systemSettingsAdmFlow;

    @Override
    @PostMapping("/register-system-settings/command")
    public CommandResponse<String> registerSystemSettings(@RequestBody RegisterSystemSettingsAdmCommand command) {
        //
        command.validate();
        SystemSettingsCdo systemSettingsCdo = command.getSystemSettingsCdo();
        String entityId = systemSettingsAdmFlow.registerSystemSettingsAdm(systemSettingsCdo);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/modify-system-settings/command")
    public CommandResponse<String> modifySystemSettings(ModifySystemSettingsAdmCommand command) {
        //
        command.validate();
        NameValueList nameValueList = command.getNameValueList();
        String entityId = systemSettingsAdmFlow.modifySystemSettingsAdm(nameValueList);
        return new CommandResponse<>(entityId);
    }
}