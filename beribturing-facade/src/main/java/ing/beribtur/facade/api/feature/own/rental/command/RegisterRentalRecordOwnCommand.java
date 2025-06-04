package ing.beribtur.facade.api.feature.own.rental.command;

import ing.beribtur.accent.message.CommandRequest;
import ing.beribtur.aggregate.rental.entity.sdo.RentalRecordCdo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class RegisterRentalRecordOwnCommand extends CommandRequest<String> {
    //
    private RentalRecordCdo rentalRecordCdo;

    public void validate() {
        //
        Assert.notNull(rentalRecordCdo, "'rentalRecordCdo' is required.");
    }
}
