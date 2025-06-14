package ing.beribtur.facade.api.feature.own.item.command;

import ing.beribtur.accent.message.CommandRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class RemoveProductImageOwnCommand extends CommandRequest<String> {
    //
    private String imageId;

    public void validate() {
        //
        Assert.hasText(imageId, "'imageId' is required.");
    }
}