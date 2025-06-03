package ing.beribtur.facade.api.feature.rnt.report.command;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.CommandRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class ModifyReportRntCommand extends CommandRequest<String> {
    //
    private String reportId;
    private NameValueList nameValueList;

    public void validate() {
        //
        Assert.hasText(reportId, "'reportId' is required.");
        Assert.notNull(nameValueList, "'nameValueList' is required.");
    }
}
