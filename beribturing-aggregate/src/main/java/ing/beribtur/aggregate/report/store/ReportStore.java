package ing.beribtur.aggregate.report.store;


import ing.beribtur.aggregate.report.entity.Report;

import java.util.List;

public interface ReportStore {
    //
    void create(Report report);
    Report retrieve(String id);
    List<Report> retrieveAll(List<String> ids);
    void update(Report report);
    void delete(String id);
}
