package ing.beribtur.facade.api.feature.adm.report.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.report.entity.Report;
import ing.beribtur.facade.api.feature.adm.report.query.FindReportAdmQuery;
import ing.beribtur.facade.api.feature.adm.report.query.FindReportsByRecordAdmQuery;
import ing.beribtur.facade.api.feature.adm.report.query.FindReportsByReporterAdmQuery;
import ing.beribtur.facade.api.feature.adm.report.query.FindReportsByResolvedStateAdmQuery;
import ing.beribtur.feature.adm.report.seek.ReportAdmSeek;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/admin/report")
public class ReportAdmSeekResource implements ReportAdmSeekFacade {
    //
    private final ReportAdmSeek reportAdmSeek;

    @Override
    @PostMapping("/find-report/query")
    public QueryResponse<Report> findReport(@RequestBody FindReportAdmQuery query) {
        //
        query.validate();
        String reportId = query.getReportId();
        Report response = reportAdmSeek.findReportById(reportId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-reports-by-record/query")
    public QueryResponse<List<Report>> findReportsByRecord(@RequestBody FindReportsByRecordAdmQuery query) {
        //
        query.validate();
        String recordId = query.getRecordId();
        List<Report> response = reportAdmSeek.findReportsByRecordId(recordId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-reports-by-reporter/query")
    public QueryResponse<List<Report>> findReportsByReporter(FindReportsByReporterAdmQuery query) {
        query.validate();
        String reporterId = query.getReporterId();
        List<Report> response = reportAdmSeek.findReportsByReporterId(reporterId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-reports-by-resolved-state/query")
    public QueryResponse<List<Report>> findReportsByResolvedState(FindReportsByResolvedStateAdmQuery query) {
        query.validate();
        Boolean resolved = query.getResolved();
        List<Report> response = reportAdmSeek.findReportsByResolved(resolved);
        return new QueryResponse<>(response);
    }
}

