package ing.beribtur.facade.api.feature.adm.item.command;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.CommandRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class ModifyProductImageAdmCommand extends CommandRequest<String> {
    //
    private String imageId;
    private NameValueList nameValueList;

    public void validate() {
        Assert.hasText(imageId, "'imageId' is required.");
        Assert.notNull(nameValueList, "'nameValueList' is required.");
    }
}
