package ing.beribtur.feature.own.rental.seek;

import ing.beribtur.accent.context.SpaceContext;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.account.entity.vo.Role;
import ing.beribtur.aggregate.rental.entity.ItemConditionCheck;
import ing.beribtur.aggregate.rental.entity.ItemConditionPhoto;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.rental.entity.vo.RentalStatus;
import ing.beribtur.aggregate.rental.entity.vo.ReservationStatus;
import ing.beribtur.aggregate.rental.logic.ItemConditionCheckLogic;
import ing.beribtur.aggregate.rental.logic.ItemConditionPhotoLogic;
import ing.beribtur.aggregate.rental.logic.RentalRecordLogic;
import ing.beribtur.aggregate.user.entity.Lender;
import ing.beribtur.aggregate.user.logic.LenderLogic;
import ing.beribtur.feature.shared.action.AuthHelper;
import ing.beribtur.feature.shared.customstore.RentalRecordCustomStore;
import ing.beribtur.feature.shared.customstore.ReservationCustomStore;
import ing.beribtur.feature.shared.sdo.RentalRecordRdo;
import ing.beribtur.feature.shared.sdo.ReservationRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RentalOwnSeek {
    //
    private final RentalRecordLogic rentalRecordLogic;
    private final ItemConditionCheckLogic itemConditionCheckLogic;
    private final ItemConditionPhotoLogic itemConditionPhotoLogic;
    private final RentalRecordCustomStore rentalRecordCustomStore;
    private final ReservationCustomStore reservationCustomStore;
    private final AuthHelper authHelper;
    private final LenderLogic lenderLogic;

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

    public Page<ReservationRdo> findReservationRdos(ReservationStatus status, Offset offset) {
        //
        String ownerId = authHelper.currentUserId(Role.ROLE_OWNER);
        return reservationCustomStore.findReservationRdos(ownerId, status, offset);
    }


    public Page<RentalRecordRdo> findRentalRecords(RentalStatus status, String searchKeyword, Offset offset) {
        //
        String username = SpaceContext.get().getUsername();
        Lender lender = lenderLogic.findByPhoneNumber(username);
        return rentalRecordCustomStore.findRentalRecords(lender.getId(), status, searchKeyword, offset);
    }
}
