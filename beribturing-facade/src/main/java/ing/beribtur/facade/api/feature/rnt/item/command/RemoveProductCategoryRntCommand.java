package ing.beribtur.facade.api.feature.rnt.item.command;

import ing.beribtur.accent.message.CommandRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class RemoveProductCategoryRntCommand extends CommandRequest<String> {
    //
    private String categoryId;

    public void validate() {
        //
        Assert.hasText(categoryId, "'categoryId' is required.");
    }
}