package ing.beribtur.facade.api.feature.rnt.item.command;

import ing.beribtur.accent.message.CommandRequest;
import ing.beribtur.aggregate.item.entity.sdo.ProductImageCdo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class RegisterProductImageRntCommand extends CommandRequest<String> {
    //
    private ProductImageCdo productImageCdo;

    public void validate() {
        //
        Assert.notNull(productImageCdo, "'productImageCdo' is required.");
    }
}