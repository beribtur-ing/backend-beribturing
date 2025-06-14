package ing.beribtur.feature.shared.customstore;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.report.entity.Report;
import org.springframework.data.domain.Page;

public interface ReportCustomStore {
    //
    Page<Report> findReportsByProductVariantId(String productVariantId, Offset offset);
}
