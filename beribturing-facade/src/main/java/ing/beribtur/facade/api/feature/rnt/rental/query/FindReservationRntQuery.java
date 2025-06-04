package ing.beribtur.facade.api.feature.rnt.rental.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class FindReservationRntQuery {
    //
    private String reservationId;

    public void validate() {
        //
        Assert.hasText(reservationId, "'reservationId' is required.");
    }
}