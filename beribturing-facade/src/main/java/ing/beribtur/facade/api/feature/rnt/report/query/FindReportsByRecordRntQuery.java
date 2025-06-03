package ing.beribtur.facade.api.feature.rnt.report.query;

import ing.beribtur.accent.message.QueryRequest;
import ing.beribtur.aggregate.report.entity.Report;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.List;

@Getter
@Setter
public class FindReportsByRecordRntQuery extends QueryRequest<List<Report>> {
    //
    private String recordId;

    public void validate() {
        //
        Assert.hasText(recordId, "'recordId' is required.");
    }
}