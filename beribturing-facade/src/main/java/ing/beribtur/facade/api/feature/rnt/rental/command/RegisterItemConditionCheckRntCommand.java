package ing.beribtur.facade.api.feature.rnt.rental.command;

import ing.beribtur.accent.message.CommandRequest;
import ing.beribtur.aggregate.rental.entity.sdo.ItemConditionCheckCdo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class RegisterItemConditionCheckRntCommand extends CommandRequest<String> {
    //
    private ItemConditionCheckCdo itemConditionCheckCdo;

    public void validate() {
        //
        Assert.notNull(itemConditionCheckCdo, "'itemConditionCheckCdo' is required.");
    }
}
