package ing.beribtur.facade.api.feature.adm.report.query;

import ing.beribtur.accent.message.QueryRequest;
import ing.beribtur.aggregate.report.entity.Report;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.List;

@Getter
@Setter
public class FindReportsByResolvedStateAdmQuery extends QueryRequest<List<Report>> {
    //
    private Boolean resolved;

    public void validate() {
        //
        Assert.notNull(resolved, "'resolved' boolean state is required.");
    }
}