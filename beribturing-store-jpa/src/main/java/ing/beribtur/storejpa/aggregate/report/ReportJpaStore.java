package ing.beribtur.storejpa.aggregate.report;

import ing.beribtur.aggregate.report.entity.Report;
import ing.beribtur.aggregate.report.store.ReportStore;
import ing.beribtur.storejpa.aggregate.report.jpo.ReportJpo;
import ing.beribtur.storejpa.aggregate.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReportJpaStore implements ReportStore {

    private final ReportRepository reportRepository;

    @Override
    public void create(Report report) {
        ReportJpo reportJpo = new ReportJpo(report);
        reportRepository.save(reportJpo);
        report.setId(reportJpo.getId());
    }

    @Override
    public Report retrieve(String id) {
        ReportJpo reportJpo = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Report not found: " + id));
        return reportJpo.toDomain();
    }

    @Override
    public List<Report> retrieveAll(List<String> ids) {
        List<ReportJpo> jpos = reportRepository.findAllById(ids);
        return ReportJpo.toDomains(jpos);
    }

    @Override
    public void update(Report report) {
        ReportJpo existingJpo = reportRepository.findById(report.getId())
                .orElseThrow(() -> new IllegalArgumentException("Report not found: " + report.getId()));

        ReportJpo updatedJpo = new ReportJpo(report);
        updatedJpo.setEntityVersion(existingJpo.getEntityVersion());
        updatedJpo.setRegisteredBy(existingJpo.getRegisteredBy());
        updatedJpo.setRegisteredOn(existingJpo.getRegisteredOn());

        reportRepository.save(updatedJpo);
    }

    @Override
    public void delete(String id) {
        reportRepository.deleteById(id);
    }

    // Additional query methods (example)
    public List<Report> findByReporterId(String reporterId) {
        return ReportJpo.toDomains(reportRepository.findByReporterId(reporterId));
    }

    public List<Report> findByRecordId(String recordId) {
        return ReportJpo.toDomains(reportRepository.findByRecordId(recordId));
    }

    public List<Report> findByResolved(boolean resolved) {
        return ReportJpo.toDomains(reportRepository.findByResolved(resolved));
    }
}


