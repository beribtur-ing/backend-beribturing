package ing.beribtur.feature.adm.systemsettings.seek;

import ing.beribtur.aggregate.account.entity.SystemSettings;
import ing.beribtur.aggregate.account.logic.SystemSettingsLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SystemSettingsAdmSeek {
    //
    private final SystemSettingsLogic systemSettingsLogic;

    public SystemSettings findSystemSettingsAdm() {
        //
        String systemSettingsId = SystemSettings.genId();
        return systemSettingsLogic.findSystemSettings(systemSettingsId);
    }
}
