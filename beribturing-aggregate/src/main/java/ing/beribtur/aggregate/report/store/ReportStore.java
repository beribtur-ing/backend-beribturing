package ing.beribtur.aggregate.report.store;


import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.report.entity.Report;

import java.util.List;

public interface ReportStore {
    //
    void create(Report report);
    void createAll(List<Report> reports);
    Report retrieve(String id);
    List<Report> retrieveAll(List<String> ids);
    List<Report> retrieveList(Offset offset);
    void update(Report report);
    void delete(Report report);
    void delete(String id);
    boolean exists(String id);

    List<Report> retrieveByReporterId(String reporterId);
    List<Report> retrieveByRecordId(String recordId);
    List<Report> retrieveByResolved(Boolean resolved);

    List<Report> retrieveByReporterIdAndResolved(String reporterId, Boolean resolved);
    
    // Count methods for statistics
    long countByResolved(Boolean resolved);
}
