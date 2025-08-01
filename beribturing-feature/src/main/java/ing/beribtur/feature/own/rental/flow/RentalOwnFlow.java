package ing.beribtur.feature.own.rental.flow;

import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.rental.entity.sdo.ItemConditionCheckCdo;
import ing.beribtur.aggregate.rental.entity.sdo.ItemConditionPhotoCdo;
import ing.beribtur.aggregate.rental.entity.sdo.RentalRecordCdo;
import ing.beribtur.aggregate.rental.entity.vo.ReservationStatus;
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
public class RentalOwnFlow {
    //
    private final ReservationLogic reservationLogic;
    private final RentalRecordLogic rentalRecordLogic;
    private final ItemConditionCheckLogic itemConditionCheckLogic;
    private final ItemConditionPhotoLogic itemConditionPhotoLogic;

    public String registerRentalRecord(RentalRecordCdo rentalRecordCdo) {
        //
        return rentalRecordLogic.registerRentalRecord(rentalRecordCdo);
    }

    public String modifyRentalRecord(String rentalRecordId, NameValueList nameValueList) {
        //
        rentalRecordLogic.modifyRentalRecord(rentalRecordId, nameValueList);
        return rentalRecordId;
    }

    public String registerItemConditionCheck(ItemConditionCheckCdo itemConditionCheckCdo) {
        //
        return itemConditionCheckLogic.registerItemConditionCheck(itemConditionCheckCdo);
    }

    public String registerItemConditionPhoto(ItemConditionPhotoCdo itemConditionPhotoCdo) {
        //
        return itemConditionPhotoLogic.registerItemConditionPhoto(itemConditionPhotoCdo);
    }

    public String approveReservation(String reservationId, RentalRecordCdo rentalRecordCdo) {
        //
        if (!reservationLogic.existsReservation(reservationId)) {
            throw new IllegalArgumentException("Reservation with ID " + reservationId + " does not exist.");

        }
        NameValueList nameValueList = new NameValueList();
        nameValueList.add(new NameValue("status", ReservationStatus.Approved.name()));
        reservationLogic.modifyReservation(reservationId, nameValueList);
        return registerRentalRecord(rentalRecordCdo);
    }

    public void rejectReservation(String reservationId) {
        //
        if (!reservationLogic.existsReservation(reservationId)) {
            throw new IllegalArgumentException("Reservation with ID " + reservationId + " does not exist.");
        }
        NameValueList nameValueList = new NameValueList();
        nameValueList.add(new NameValue("status", ReservationStatus.Rejected.name()));
        reservationLogic.modifyReservation(reservationId, nameValueList);
    }
}
