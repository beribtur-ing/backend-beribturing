package ing.beribtur.facade.api.feature.adm.item.command;

import ing.beribtur.accent.message.CommandRequest;
import ing.beribtur.feature.adm.item.sdo.ProductAdmRegCdo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class RegisterProductAdmCommand extends CommandRequest<String> {
    //
    private ProductAdmRegCdo productAdmRegCdo;

    public void validate() {
        //
        Assert.notNull(productAdmRegCdo, "'productAdmRegCdo' is required.");
    }
}
