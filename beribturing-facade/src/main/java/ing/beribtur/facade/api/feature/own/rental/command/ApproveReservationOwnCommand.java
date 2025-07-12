package ing.beribtur.facade.api.feature.own.rental.command;

import ing.beribtur.accent.message.CommandRequest;
import ing.beribtur.aggregate.rental.entity.sdo.RentalRecordCdo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class ApproveReservationOwnCommand extends CommandRequest<String> {
    //
    private String reservationId;
    private RentalRecordCdo rentalRecordCdo;

    public void validate() {
        //
        Assert.hasText(reservationId, "'reservationId' must not be empty");
        Assert.isTrue(reservationId.trim().equals(reservationId), "'reservationId' must not contain leading or trailing spaces");
        Assert.notNull(rentalRecordCdo, "'rentalRecordCdo' is required.");
    }
}