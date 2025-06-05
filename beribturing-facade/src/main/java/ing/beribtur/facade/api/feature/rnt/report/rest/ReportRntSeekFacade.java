package ing.beribtur.facade.api.feature.rnt.report.rest;


import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.report.entity.Report;
import ing.beribtur.facade.api.feature.rnt.report.query.FindReportRntQuery;
import ing.beribtur.facade.api.feature.rnt.report.query.FindReportsByReporterAndResolvedStateRntQuery;
import ing.beribtur.facade.api.feature.rnt.report.query.FindReportsByReporterRntQuery;

import java.util.List;

public interface ReportRntSeekFacade {
    //
    QueryResponse<Report> findReport(FindReportRntQuery query);
    QueryResponse<List<Report>> findReportsByReporter(FindReportsByReporterRntQuery query);
    QueryResponse<List<Report>> findReportsByResolvedState(FindReportsByReporterAndResolvedStateRntQuery query);
}
