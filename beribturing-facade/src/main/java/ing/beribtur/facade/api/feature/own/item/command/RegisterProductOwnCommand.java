package ing.beribtur.facade.api.feature.own.item.command;

import ing.beribtur.accent.message.CommandRequest;
import ing.beribtur.feature.own.item.sdo.ProductOwnRegCdo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class RegisterProductOwnCommand extends CommandRequest<String> {
    //
    private ProductOwnRegCdo productOwnRegCdo;

    public void validate() {
        //
        Assert.notNull(productOwnRegCdo, "'productOwnRegCdo' is required.");
    }
}
