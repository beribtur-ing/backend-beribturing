package ing.beribtur.facade.api.feature.rnt.report.query;

import ing.beribtur.accent.message.QueryRequest;
import ing.beribtur.aggregate.report.entity.Report;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.List;

@Getter
@Setter
public class FindReportsByReporterAndResolvedStateRntQuery extends QueryRequest<List<Report>> {
    //
    private String reporterId;
    private Boolean resolved;

    public void validate() {
        //
        Assert.notNull(reporterId, "'reporterId'  is required.");
        Assert.notNull(resolved, "'resolved' boolean state is required.");
    }
}