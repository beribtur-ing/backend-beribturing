package ing.beribtur.feature.own.rental.seek;

import ing.beribtur.accent.context.SpaceContext;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.account.entity.vo.Role;
import ing.beribtur.aggregate.rental.entity.ItemConditionCheck;
import ing.beribtur.aggregate.rental.entity.ItemConditionPhoto;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.rental.entity.Reservation;
import ing.beribtur.aggregate.rental.entity.vo.RentalStatus;
import ing.beribtur.aggregate.rental.entity.vo.ReservationStatus;
import ing.beribtur.aggregate.rental.logic.ItemConditionCheckLogic;
import ing.beribtur.aggregate.rental.logic.ItemConditionPhotoLogic;
import ing.beribtur.aggregate.rental.logic.RentalRecordLogic;
import ing.beribtur.aggregate.rental.logic.ReservationLogic;
import ing.beribtur.feature.shared.action.AuthHelper;
import ing.beribtur.feature.shared.customstore.RentalRecordCustomStore;
import ing.beribtur.feature.shared.sdo.RentalRecordRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RentalOwnSeek {
    //
    private final ReservationLogic reservationLogic;
    private final RentalRecordLogic rentalRecordLogic;
    private final ItemConditionCheckLogic itemConditionCheckLogic;
    private final ItemConditionPhotoLogic itemConditionPhotoLogic;
    private final RentalRecordCustomStore rentalRecordCustomStore;
    private final AuthHelper authHelper;

    public RentalRecord findRentalRecordById(String rentalRecordId) {
        //
        return rentalRecordLogic.findRentalRecord(rentalRecordId);
    }

    public ItemConditionCheck findItemConditionCheckById(String itemConditionCheckId) {
        //
        return itemConditionCheckLogic.findItemConditionCheck(itemConditionCheckId);
    }

    public ItemConditionPhoto findItemConditionPhotoById(String itemConditionPhotoId) {
        //
        return itemConditionPhotoLogic.findItemConditionPhoto(itemConditionPhotoId);
    }

    public List<Reservation> findReservations(ReservationStatus status, Offset offset) {
        //
        String ownerId = authHelper.currentUserId(Role.ROLE_OWNER);
        return reservationLogic.findAllByOwnerId(ownerId, status, offset);
    }

    public Page<RentalRecordRdo> findRentalRecords(RentalStatus status, String searchKeyword, Offset offset) {
        //
        String username = SpaceContext.get().getUsername();
        return rentalRecordCustomStore.findRentalRecords(username, status, searchKeyword, offset);
    }
}