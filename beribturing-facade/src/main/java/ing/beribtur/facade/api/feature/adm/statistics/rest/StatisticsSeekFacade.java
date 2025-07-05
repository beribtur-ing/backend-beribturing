package ing.beribtur.facade.api.feature.adm.statistics.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.feature.adm.statistic.rdo.AdminOverviewRdo;

public interface StatisticsSeekFacade {
    //
    QueryResponse<AdminOverviewRdo> findAdminOverview();
}
