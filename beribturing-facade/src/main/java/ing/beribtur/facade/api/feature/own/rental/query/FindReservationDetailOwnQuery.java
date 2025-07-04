package ing.beribtur.facade.api.feature.own.rental.query;

import ing.beribtur.accent.message.QueryRequest;
import ing.beribtur.feature.shared.sdo.ReservationDetailRdo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class FindReservationDetailOwnQuery extends QueryRequest<ReservationDetailRdo> {
    //
    private String reservationId;

    public void validate() {
        //
        Assert.hasText(reservationId, "'reservationId' is required.");
    }
}
