package ing.beribtur.facade.api.feature.own.item.command;

import ing.beribtur.accent.message.CommandRequest;
import ing.beribtur.feature.shared.sdo.ProductVariantRegCdo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class RegisterProductVariantOwnCommand extends CommandRequest<String> {
    //
    private ProductVariantRegCdo productVariantRegCdo;

    public void validate() {
        //
        Assert.notNull(productVariantRegCdo, "'productVariantRegCdo' is required.");
    }
}
