package ing.beribtur.aggregate.report.logic;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.util.Entities;
import ing.beribtur.aggregate.report.entity.Report;
import ing.beribtur.aggregate.report.entity.sdo.ReportCdo;
import ing.beribtur.aggregate.report.store.ReportStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportLogic {
    //
    private final ReportStore reportStore;

    public String registerReport(ReportCdo reportCdo) {
        //
        Report report = new Report(reportCdo);
        reportStore.create(report);
        return report.getId();
    }

    public List<String> registerReports(List<ReportCdo> reportCdos) {
        //
        return reportCdos.stream()
                .map(this::registerReport)
                .collect(Collectors.toList());
    }

    public Report findReport(String reportId) {
        //
        return reportStore.retrieve(reportId);
    }

    public List<Report> findReports(List<String> reportIds) {
        //
        return reportStore.retrieveAll(reportIds);
    }

    public List<Report> findReports(Offset offset) {
        //
        return reportStore.retrieveList(offset);
    }

    public void modifyReport(String reportId, NameValueList nameValues) {
        //
        Report report = findReport(reportId);
        report.modify(nameValues);
        reportStore.update(report);
    }

    public void modifyReport(Report report) {
        //
        Report oldReport = findReport(report.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldReport, report);
        if (!nameValues.list().isEmpty()) {
            modifyReport(report.getId(), nameValues);
        }
    }

    public void removeReport(String reportId) {
        //
        reportStore.delete(reportId);
    }

    public boolean existsReport(String reportId) {
        //
        return reportStore.exists(reportId);
    }

    public List<Report> findReportsByReporterId(String reporterId) {
        //
        return reportStore.retrieveByReporterId(reporterId);
    }

    public List<Report> findReportsByRecordId(String recordId) {
        //
        return reportStore.retrieveByRecordId(recordId);
    }

    public List<Report> findReportsByResolved(Boolean resolved) {
        //
        return reportStore.retrieveByResolved(resolved);
    }

    public String resolve(String reportId) {
        //
        Report report = findReport(reportId);
        if(report.isResolved()) {
            throw new IllegalArgumentException("Report is already resolved");
        }
        report.setResolved(true);
        reportStore.update(report);
        return report.getId();
    }

    public List<Report> findReportsByReporterIdAndResolvedState(String reporterId, Boolean resolved) {
        //
        return reportStore.retrieveByReporterIdAndResolved(reporterId, resolved);
    }
}

