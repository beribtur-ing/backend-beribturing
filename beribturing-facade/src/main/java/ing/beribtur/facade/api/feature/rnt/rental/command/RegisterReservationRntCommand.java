package ing.beribtur.facade.api.feature.rnt.rental.command;

import ing.beribtur.accent.message.CommandRequest;
import ing.beribtur.aggregate.rental.entity.sdo.ReservationCdo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class RegisterReservationRntCommand extends CommandRequest<String> {
    //
    private ReservationCdo reservationCdo;

    public void validate() {
        //
        Assert.notNull(reservationCdo, "'reservationCdo' is required.");
    }
}
