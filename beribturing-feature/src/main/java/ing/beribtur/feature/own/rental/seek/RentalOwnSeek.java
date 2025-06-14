package ing.beribtur.feature.own.rental.seek;

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
import lombok.RequiredArgsConstructor;
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

    public List<Reservation> findReservations(String ownerId, ReservationStatus status) {
        //
        return reservationLogic.findAllByOwnerId(ownerId, status);
    }

    public List<RentalRecord> findRentalRecords(String ownerId, RentalStatus status) {
        //
        return rentalRecordLogic.findAllByOwnerId(ownerId, status);
    }
}