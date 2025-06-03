package ing.beribtur.facade.api.feature.item.command;

import ing.beribtur.accent.message.CommandRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class RemoveProductImageCommand extends CommandRequest<String> {
    //
    private String imageId;

    public void validate() {
        //
        Assert.hasText(imageId, "'imageId' is required.");
    }
} 