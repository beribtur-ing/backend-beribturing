package ing.beribtur.facade.api.feature.adm.systemsettings.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.account.entity.SystemSettings;
import ing.beribtur.facade.api.feature.adm.systemsettings.query.FindSystemSettingsAdmQuery;
import ing.beribtur.feature.adm.systemsettings.seek.SystemSettingsAdmSeek;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feature/admin/system-settings")
@RequiredArgsConstructor
public class SystemSettingsAdmSeekResource implements SystemSettingsAdmSeekFacade {
    //
    private final SystemSettingsAdmSeek systemSettingsAdmSeek;

    @Override
    @PostMapping("/find-system-settings/query")
    public QueryResponse<SystemSettings> findSystemSettings(FindSystemSettingsAdmQuery query) {
        query.validate();
        SystemSettings systemSettings = systemSettingsAdmSeek.findSystemSettingsAdm();
        return new QueryResponse<>(systemSettings);
    }
}
