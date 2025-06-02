package ing.beribtur.storejpa.aggregate.report.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.report.entity.Report;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "REPORT")
public class ReportJpo extends DomainEntityJpo {

    @Column(nullable = false)
    private String reporterId;

    @Column(nullable = false, length = 1000)
    private String reason;

    @Column(nullable = false)
    private LocalDateTime reportDate;

    @Column(nullable = false)
    private boolean resolved;

    @Column(nullable = false)
    private String recordId;

    public ReportJpo(Report report) {
        //
        super(report);
        BeanUtils.copyProperties(report, this);
    }

    public Report toDomain() {
        //
        Report report = new Report();
        BeanUtils.copyProperties(this, report);
        return report;
    }

    public static List<Report> toDomains(List<ReportJpo> jpos) {
        //
        return jpos.stream().map(ReportJpo::toDomain).toList();
    }
}

