package ing.beribtur.facade.api.feature.own.item.command;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.CommandRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class ModifyProductOwnCommand extends CommandRequest<String> {
    //
    private String productId;
    private NameValueList nameValueList;

    public void validate() {
        //
        Assert.hasText(productId, "'productId' is required.");
        Assert.notNull(nameValueList, "'nameValueList' is required.");
    }
}