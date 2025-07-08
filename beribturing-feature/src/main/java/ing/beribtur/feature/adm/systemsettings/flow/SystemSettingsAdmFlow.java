package ing.beribtur.feature.adm.systemsettings.flow;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.account.entity.SystemSettings;
import ing.beribtur.aggregate.account.entity.sdo.SystemSettingsCdo;
import ing.beribtur.aggregate.account.logic.SystemSettingsLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SystemSettingsAdmFlow {
    //
    private final SystemSettingsLogic systemSettingsLogic;

    public String registerSystemSettingsAdm(SystemSettingsCdo systemSettingsCdo) {
        //
        return systemSettingsLogic.registerSystemSettings(systemSettingsCdo);
    }

    public String modifySystemSettingsAdm(NameValueList nameValueList) {
        //
        String systemSettingsId = SystemSettings.genId();
        systemSettingsLogic.modifySystemSettings(systemSettingsId, nameValueList);
        return systemSettingsId;
    }
}