package ing.beribtur.facade.api.feature.own.rental.query;

import ing.beribtur.accent.message.OffsetQueryRequest;
import ing.beribtur.accent.message.QueryRequest;
import ing.beribtur.aggregate.rental.entity.Reservation;
import ing.beribtur.aggregate.rental.entity.vo.ReservationStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class FindReservationsOwnQuery extends OffsetQueryRequest<List<Reservation>> {
    //
    private String ownerId;
    private ReservationStatus status;

    public void validate() {
        //
        Assert.hasText(ownerId, "'ownerId' must not be empty");
        Assert.isTrue(ownerId.trim().equals(ownerId), "'ownerId' must not contain leading or trailing spaces");
        if (status != null) {
            Assert.isTrue(Arrays.stream(ReservationStatus.values()).anyMatch(s -> s.equals(status)), "'status' is invalid");
        }
    }
}