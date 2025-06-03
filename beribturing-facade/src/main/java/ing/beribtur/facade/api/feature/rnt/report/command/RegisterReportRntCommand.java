package ing.beribtur.facade.api.feature.rnt.report.command;

import ing.beribtur.accent.message.CommandRequest;
import ing.beribtur.aggregate.report.entity.sdo.ReportCdo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class RegisterReportRntCommand extends CommandRequest<String> {
    //
    private ReportCdo reportCdo;

    public void validate() {
        //
        Assert.notNull(reportCdo, "'reportCdo' is required.");
    }
}
