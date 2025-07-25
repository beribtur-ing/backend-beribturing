package ing.beribtur.storejpa.aggregate.report.repository;

import ing.beribtur.storejpa.aggregate.report.jpo.ReportJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<ReportJpo, String> {
    List<ReportJpo> findByReporterId(String reporterId);
    List<ReportJpo> findByRecordId(String recordId);
    List<ReportJpo> findByResolved(Boolean resolved);

    List<ReportJpo> findByReporterIdAndResolved(String reporterId, Boolean resolved);
    
    // Count methods for statistics
    long countByResolved(Boolean resolved);
}
