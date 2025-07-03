package ing.beribtur.facade.api.feature.own.rental.query;

import ing.beribtur.accent.message.OffsetQueryRequest;
import ing.beribtur.aggregate.rental.entity.vo.ReservationStatus;
import ing.beribtur.feature.shared.sdo.ReservationRdo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class FindReservationRdosOwnQuery extends OffsetQueryRequest<List<ReservationRdo>> {
    //
    private ReservationStatus status;

    public void validate() {
        //
        if (status != null) {
            Assert.isTrue(Arrays.stream(ReservationStatus.values()).anyMatch(s -> s.equals(status)), "'status' is invalid");
        }
    }
}
