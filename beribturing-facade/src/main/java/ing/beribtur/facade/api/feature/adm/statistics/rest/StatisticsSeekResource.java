package ing.beribtur.facade.api.feature.adm.statistics.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.feature.adm.statistic.rdo.AdminOverviewRdo;
import ing.beribtur.feature.adm.statistic.seek.StatisticsSeek;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feature/admin/statistics")
@RequiredArgsConstructor
public class StatisticsSeekResource implements StatisticsSeekFacade {
    //
    private StatisticsSeek statisticsSeek;

    @Override
    @PostMapping("/find-overview/query")
    public QueryResponse<AdminOverviewRdo> findAdminOverview() {
        //
        AdminOverviewRdo rdo = statisticsSeek.findOverallOverview();
        return new QueryResponse<>(rdo);
    }
}
