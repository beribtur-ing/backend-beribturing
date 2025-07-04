package ing.beribtur.facade.api.feature.rnt.report.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.report.entity.Report;
import ing.beribtur.facade.api.feature.rnt.report.query.FindReportRntQuery;
import ing.beribtur.facade.api.feature.rnt.report.query.FindReportsByReporterAndResolvedStateRntQuery;
import ing.beribtur.facade.api.feature.rnt.report.query.FindReportsByReporterRntQuery;
import ing.beribtur.feature.rnt.report.seek.ReportRntSeek;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/renter/report")
public class ReportRntSeekResource implements ReportRntSeekFacade {
    //
    private final ReportRntSeek reportRntSeek;

    @Override
    @PostMapping("/find-report/query")
    public QueryResponse<Report> findReport(@RequestBody FindReportRntQuery query) {
        //
        query.validate();
        String reportId = query.getReportId();
        Report response = reportRntSeek.findReportById(reportId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-reports-by-reporter/query")
    public QueryResponse<List<Report>> findReportsByReporter(@RequestBody FindReportsByReporterRntQuery query) {
        query.validate();
        String reporterId = query.getReporterId();
        List<Report> response = reportRntSeek.findReportsByReporterId(reporterId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-reports-by-reporter-and-resolved-state/query")
    public QueryResponse<List<Report>> findReportsByResolvedState(@RequestBody FindReportsByReporterAndResolvedStateRntQuery query) {
        query.validate();
        String reporterId = query.getReporterId();
        Boolean resolved = query.getResolved();
        List<Report> response = reportRntSeek.findReportsByReporterIdAndResolvedState(reporterId, resolved);
        return new QueryResponse<>(response);
    }
}

