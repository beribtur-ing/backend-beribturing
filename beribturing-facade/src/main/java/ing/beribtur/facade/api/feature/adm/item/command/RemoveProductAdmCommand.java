package ing.beribtur.facade.api.feature.adm.item.command;

import ing.beribtur.accent.message.CommandRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;


@Getter
@Setter
public class RemoveProductAdmCommand extends  CommandRequest<String> {
    //
    private String productId;

    public void validate() {
        //
        Assert.hasText(productId, "'productId' is required.");
    }
}
