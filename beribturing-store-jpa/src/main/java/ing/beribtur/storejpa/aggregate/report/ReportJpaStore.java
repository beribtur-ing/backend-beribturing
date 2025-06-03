package ing.beribtur.storejpa.aggregate.report;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.report.entity.Report;
import ing.beribtur.aggregate.report.store.ReportStore;
import ing.beribtur.storejpa.aggregate.report.jpo.ReportJpo;
import ing.beribtur.storejpa.aggregate.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReportJpaStore implements ReportStore {

    private final ReportRepository reportRepository;

    @Override
    public void create(Report report) {
        ReportJpo reportJpo = new ReportJpo(report);
        reportRepository.save(reportJpo);
    }

    @Override
    public void createAll(List<Report> reports) {
        //
        if (reports == null) {
            return;
        }
        reports.forEach(this::create);
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
    public List<Report> retrieveList(Offset offset) {
        //
        Pageable pageable = PageRequest.of(offset.page(), offset.limit());
        return reportRepository.findAll(pageable).map(ReportJpo::toDomain).toList();
    }

    @Override
    public void update(Report report) {
        ReportJpo reportJpo = new ReportJpo(report);
        reportRepository.save(reportJpo);
    }

    @Override
    public void delete(Report report) {
        //
        reportRepository.deleteById(report.getId());
    }

    @Override
    public void delete(String id) {
        //
        reportRepository.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        //
        Optional<ReportJpo> reportJpo = reportRepository.findById(id);
        return reportJpo.isPresent();
    }

    // Additional query methods (example)
    public List<Report> retrieveByReporterId(String reporterId) {
        //
        return ReportJpo.toDomains(reportRepository.findByReporterId(reporterId));
    }

    public List<Report> retrieveByRecordId(String recordId) {
        //
        return ReportJpo.toDomains(reportRepository.findByRecordId(recordId));
    }

    public List<Report> retrieveByResolved(boolean resolved) {
        //
        return ReportJpo.toDomains(reportRepository.findByResolved(resolved));
    }
}


