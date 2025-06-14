package ing.beribtur.facade.api.feature.own.item.command;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.CommandRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class ModifyProductVariantOwnCommand extends CommandRequest<String> {
    //
    private String variantId;
    private NameValueList nameValueList;

    public void validate() {
        //
        Assert.hasText(variantId, "'variantId' is required.");
        Assert.notNull(nameValueList, "'nameValueList' is required.");
    }
}