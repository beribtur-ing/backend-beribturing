package ing.beribtur.feature.shared.customstore;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.rental.entity.vo.RentalStatus;
import ing.beribtur.feature.shared.sdo.RentalRecordRdo;
import org.springframework.data.domain.Page;

public interface RentalRecordCustomStore {
    //
    Page<RentalRecordRdo> findRentalRecordsByOwner(String ownerId, RentalStatus status, String searchKeyword, Offset offset);
    Page<RentalRecordRdo> findRentalRecordsByLendee(String lendeeId, Offset offset);
}
