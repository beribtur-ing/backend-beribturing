package ing.beribtur.facade.api.feature.rnt.report.command;

import ing.beribtur.accent.message.CommandRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;


@Getter
@Setter
public class RemoveReportRntCommand extends  CommandRequest<String> {
    //
    private String reportId;

    public void validate() {
        //
        Assert.hasText(reportId, "'reportId' is required.");
    }
}
