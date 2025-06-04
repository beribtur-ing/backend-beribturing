package ing.beribtur.facade.api.feature.rnt.rental.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.rental.entity.ItemConditionCheck;
import ing.beribtur.aggregate.rental.entity.ItemConditionPhoto;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.rental.entity.Reservation;
import ing.beribtur.facade.api.feature.rnt.rental.query.FindItemConditionCheckRntQuery;
import ing.beribtur.facade.api.feature.rnt.rental.query.FindItemConditionPhotoRntQuery;
import ing.beribtur.facade.api.feature.rnt.rental.query.FindRentalRecordRntQuery;
import ing.beribtur.facade.api.feature.rnt.rental.query.FindReservationRntQuery;
import ing.beribtur.feature.rnt.rental.seek.RentalRntSeek;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/renter/rental")
public class RentalRntSeekResource implements RentalRntSeekFacade {
    //
    private final RentalRntSeek rentalRntSeek;

    @Override
    @PostMapping("/find-rental-record/query")
    public QueryResponse<RentalRecord> findRentalRecord(@RequestBody FindRentalRecordRntQuery query) {
        //
        query.validate();
        String rentalRecordId = query.getRentalRecordId();
        RentalRecord response = rentalRntSeek.findRentalRecordById(rentalRecordId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-reservation/query")
    public QueryResponse<Reservation> findReservation(@RequestBody FindReservationRntQuery query) {
        //
        query.validate();
        String reservationId = query.getReservationId();
        Reservation response = rentalRntSeek.findReservationById(reservationId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-item-condition-check/query")
    public QueryResponse<ItemConditionCheck> findItemConditionCheck(@RequestBody FindItemConditionCheckRntQuery query) {
        //
        query.validate();
        String itemConditionCheckId = query.getItemConditionCheckId();
        ItemConditionCheck response = rentalRntSeek.findItemConditionCheckById(itemConditionCheckId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-item-condition-photo/query")
    public QueryResponse<ItemConditionPhoto> findItemConditionPhoto(@RequestBody FindItemConditionPhotoRntQuery query) {
        //
        query.validate();
        String itemConditionPhotoId = query.getItemConditionPhotoId();
        ItemConditionPhoto response = rentalRntSeek.findItemConditionPhotoById(itemConditionPhotoId);
        return new QueryResponse<>(response);
    }
}