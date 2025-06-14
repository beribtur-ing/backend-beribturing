package ing.beribtur.facade.api.feature.rnt.item.command;

import ing.beribtur.accent.message.CommandRequest;
import ing.beribtur.aggregate.item.entity.sdo.ProductCdo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class RegisterProductRntCommand extends CommandRequest<String> {
    //
    private ProductCdo productCdo;

    public void validate() {
        //
        Assert.notNull(productCdo, "'productCdo' is required.");
    }
}