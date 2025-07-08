package ing.beribtur.facade.api.feature.adm.systemsettings.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.account.entity.SystemSettings;
import ing.beribtur.facade.api.feature.adm.systemsettings.query.FindSystemSettingsAdmQuery;

public interface SystemSettingsAdmSeekFacade {
    //
    QueryResponse<SystemSettings> findSystemSettings(FindSystemSettingsAdmQuery query);
}