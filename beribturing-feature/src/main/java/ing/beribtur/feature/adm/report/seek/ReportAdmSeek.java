package ing.beribtur.feature.adm.report.seek;

import ing.beribtur.aggregate.report.entity.Report;
import ing.beribtur.aggregate.report.logic.ReportLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportAdmSeek {
    //
    private final ReportLogic reportLogic;

    public Report findReportById(String reportId) {
        //
        return reportLogic.findReport(reportId);
    }

    public List<Report> findReportsByRecordId(String recordId) {
        //
        return reportLogic.findReportsByRecordId(recordId);
    }

    public List<Report> findReportsByReporterId(String reporterId) {
        //
        return reportLogic.findReportsByReporterId(reporterId);
    }

    public List<Report> findReportsByResolved(Boolean resolved) {
        //
        return reportLogic.findReportsByResolved(resolved);
    }
}

