package ing.beribtur.facade.api.feature.rnt.rental.command;

import ing.beribtur.accent.message.CommandRequest;
import ing.beribtur.aggregate.rental.entity.sdo.ItemConditionPhotoCdo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class RegisterItemConditionPhotoRntCommand extends CommandRequest<String> {
    //
    private ItemConditionPhotoCdo itemConditionPhotoCdo;

    public void validate() {
        //
        Assert.notNull(itemConditionPhotoCdo, "'itemConditionPhotoCdo' is required.");
    }
}
