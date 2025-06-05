package ing.beribtur.facade.api.feature.adm.report.rest;


import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.report.entity.Report;
import ing.beribtur.facade.api.feature.adm.report.query.FindReportAdmQuery;
import ing.beribtur.facade.api.feature.adm.report.query.FindReportsByRecordAdmQuery;
import ing.beribtur.facade.api.feature.adm.report.query.FindReportsByReporterAdmQuery;
import ing.beribtur.facade.api.feature.adm.report.query.FindReportsByResolvedStateAdmQuery;

import java.util.List;

public interface ReportAdmSeekFacade {
    //
    QueryResponse<Report> findReport(FindReportAdmQuery query);
    QueryResponse<List<Report>> findReportsByRecord(FindReportsByRecordAdmQuery query);
    QueryResponse<List<Report>> findReportsByReporter(FindReportsByReporterAdmQuery query);
    QueryResponse<List<Report>> findReportsByResolvedState(FindReportsByResolvedStateAdmQuery query);
}
