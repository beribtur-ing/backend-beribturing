package ing.beribtur.facade.api.feature.rnt.report.query;

import ing.beribtur.accent.message.QueryRequest;
import ing.beribtur.aggregate.report.entity.Report;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class FindReportRntQuery extends QueryRequest<Report> {
    //
    private String reportId;

    public void validate() {
        //
        Assert.hasText(reportId, "'reportId' is required.");
    }
}
