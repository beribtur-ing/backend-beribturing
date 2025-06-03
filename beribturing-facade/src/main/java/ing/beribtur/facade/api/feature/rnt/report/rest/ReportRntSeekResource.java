package ing.beribtur.facade.api.feature.rnt.report.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.report.entity.Report;
import ing.beribtur.facade.api.feature.rnt.report.query.FindReportRntQuery;
import ing.beribtur.facade.api.feature.rnt.report.query.FindReportsByRecordRntQuery;
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
    @PostMapping("/find-reports-by-record/query")
    public QueryResponse<List<Report>> findReportsByRecord(@RequestBody FindReportsByRecordRntQuery query) {
        //
        query.validate();
        String recordId = query.getRecordId();
        List<Report> response = reportRntSeek.findReportsByRecordId(recordId);
        return new QueryResponse<>(response);
    }
}

