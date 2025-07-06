package ing.beribtur.facade.api.feature.adm.lendee.command;

import ing.beribtur.accent.message.CommandRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class ModifyLendeeStatusCommand extends CommandRequest<String> {
    //
    private String lendeeId;

    public void validate() {
        //
        Assert.hasText(lendeeId, "lendeeId is required");
    }
}
