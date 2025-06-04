package ing.beribtur.facade.api.feature.own.rental.command;

import ing.beribtur.accent.message.CommandRequest;
import ing.beribtur.aggregate.rental.entity.sdo.ItemConditionCheckCdo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class RegisterItemConditionCheckOwnCommand extends CommandRequest<String> {
    //
    private ItemConditionCheckCdo itemConditionCheckCdo;

    public void validate() {
        //
        Assert.notNull(itemConditionCheckCdo, "'itemConditionCheckCdo' is required.");
    }
}
