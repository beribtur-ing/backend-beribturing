package ing.beribtur.facade.api.feature.own.report.rest;


import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.report.entity.Report;
import ing.beribtur.facade.api.feature.own.report.query.FindReportsByProductVariantIdOwnQuery;

import java.util.List;

public interface ReportOwnSeekFacade {
    //
    QueryResponse<List<Report>> findReportsByProductVariantId(FindReportsByProductVariantIdOwnQuery query);
}
