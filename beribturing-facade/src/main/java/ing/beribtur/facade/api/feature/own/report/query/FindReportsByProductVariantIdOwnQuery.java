package ing.beribtur.facade.api.feature.own.report.query;

import ing.beribtur.accent.message.OffsetQueryRequest;
import ing.beribtur.aggregate.report.entity.Report;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.List;

@Getter
@Setter
public class FindReportsByProductVariantIdOwnQuery extends OffsetQueryRequest<List<Report>> {
    //
    private String productVariantId;

    public void validate() {
        //
        Assert.hasText(productVariantId, "'productVariantId' is required.");
    }
}