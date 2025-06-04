package ing.beribtur.feature.rnt.rental.seek;

import ing.beribtur.aggregate.rental.entity.ItemConditionCheck;
import ing.beribtur.aggregate.rental.entity.ItemConditionPhoto;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.rental.entity.Reservation;
import ing.beribtur.aggregate.rental.logic.ItemConditionCheckLogic;
import ing.beribtur.aggregate.rental.logic.ItemConditionPhotoLogic;
import ing.beribtur.aggregate.rental.logic.RentalRecordLogic;
import ing.beribtur.aggregate.rental.logic.ReservationLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RentalRntSeek {
    //
    private final RentalRecordLogic rentalRecordLogic;
    private final ReservationLogic reservationLogic;
    private final ItemConditionCheckLogic itemConditionCheckLogic;
    private final ItemConditionPhotoLogic itemConditionPhotoLogic;

    public RentalRecord findRentalRecordById(String rentalRecordId) {
        //
        return rentalRecordLogic.findRentalRecord(rentalRecordId);
    }

    public Reservation findReservationById(String reservationId) {
        //
        return reservationLogic.findReservation(reservationId);
    }

    public ItemConditionCheck findItemConditionCheckById(String itemConditionCheckId) {
        //
        return itemConditionCheckLogic.findItemConditionCheck(itemConditionCheckId);
    }

    public ItemConditionPhoto findItemConditionPhotoById(String itemConditionPhotoId) {
        //
        return itemConditionPhotoLogic.findItemConditionPhoto(itemConditionPhotoId);
    }
}