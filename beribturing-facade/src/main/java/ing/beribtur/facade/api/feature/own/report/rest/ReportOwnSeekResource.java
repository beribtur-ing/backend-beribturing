package ing.beribtur.facade.api.feature.own.report.rest;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.report.entity.Report;
import ing.beribtur.facade.api.feature.own.report.query.FindReportsByProductVariantIdOwnQuery;
import ing.beribtur.feature.own.report.seek.ReportOwnSeek;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/owner/report")
public class ReportOwnSeekResource implements ReportOwnSeekFacade {
    //
    private final ReportOwnSeek reportOwnSeek;

    @Override
    @PostMapping("/find-reports-by-product-variant-id/query")
    public QueryResponse<List<Report>> findReportsByProductVariantId(FindReportsByProductVariantIdOwnQuery query) {
        //
        query.validate();
        String productVariantId = query.getProductVariantId();
        Offset offset = query.getOffset();
        Page<Report> response = reportOwnSeek.findReportsByProductVariantId(productVariantId, offset);
        query.setResponse(response);
        return query.getResponse();
    }
}

