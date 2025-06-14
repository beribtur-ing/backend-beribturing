package ing.beribtur.facade.api.feature.rnt.item.command;

import ing.beribtur.accent.message.CommandRequest;
import ing.beribtur.aggregate.item.entity.sdo.ProductVariantCdo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class RegisterProductVariantRntCommand extends CommandRequest<String> {
    //
    private ProductVariantCdo productVariantCdo;

    public void validate() {
        //
        Assert.notNull(productVariantCdo, "'productVariantCdo' is required.");
    }
}