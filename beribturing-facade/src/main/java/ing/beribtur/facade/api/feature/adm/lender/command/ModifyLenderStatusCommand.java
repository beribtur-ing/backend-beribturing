package ing.beribtur.facade.api.feature.adm.lender.command;

import ing.beribtur.accent.message.CommandRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class ModifyLenderStatusCommand extends CommandRequest<String> {
    //
    private String lenderId;

    public void validate() {
        //
        Assert.hasText(lenderId, "lenderId is required");
    }
}
