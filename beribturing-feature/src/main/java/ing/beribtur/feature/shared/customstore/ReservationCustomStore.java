package ing.beribtur.feature.shared.customstore;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.rental.entity.vo.ReservationStatus;
import ing.beribtur.feature.shared.sdo.ReservationRdo;
import org.springframework.data.domain.Page;

public interface ReservationCustomStore {
    //
    Page<ReservationRdo> findReservationRdos(String ownerId, ReservationStatus status, Offset offset);
}
