package ing.beribtur.feature.own.report.seek;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.report.entity.Report;
import ing.beribtur.feature.shared.customstore.ReportCustomStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportOwnSeek {
    //
    private final ReportCustomStore reportCustomStore;

    public Page<Report> findReportsByProductVariantId(String productVariantId, Offset offset) {
        //
        return reportCustomStore.findReportsByProductVariantId(productVariantId, offset);
    }
}

