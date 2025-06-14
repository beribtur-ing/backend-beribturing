package ing.beribtur.facade.api.feature.own.item.command;

import ing.beribtur.accent.message.CommandRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class RemoveProductVariantOwnCommand extends CommandRequest<String> {
    //
    private String variantId;

    public void validate() {
        //
        Assert.hasText(variantId, "'variantId' is required.");
    }
}