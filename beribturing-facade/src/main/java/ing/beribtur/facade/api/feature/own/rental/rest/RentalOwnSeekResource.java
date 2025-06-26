package ing.beribtur.facade.api.feature.own.rental.rest;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.rental.entity.ItemConditionCheck;
import ing.beribtur.aggregate.rental.entity.ItemConditionPhoto;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.rental.entity.Reservation;
import ing.beribtur.aggregate.rental.entity.vo.RentalStatus;
import ing.beribtur.aggregate.rental.entity.vo.ReservationStatus;
import ing.beribtur.facade.api.feature.own.rental.query.*;
import ing.beribtur.feature.own.rental.seek.RentalOwnSeek;
import ing.beribtur.feature.shared.sdo.RentalRecordRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/owner/rental")
public class RentalOwnSeekResource implements RentalOwnSeekFacade {
    //
    private final RentalOwnSeek rentalOwnSeek;

    @Override
    @PostMapping("/find-reservations/query")
    public QueryResponse<List<Reservation>> findReservations(FindReservationsOwnQuery query) {
        //
        query.validate();
        String ownerId = query.getOwnerId();
        ReservationStatus status = query.getStatus();
        List<Reservation> response = rentalOwnSeek.findReservations(ownerId, status);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-rental-record/query")
    public QueryResponse<RentalRecord> findRentalRecord(@RequestBody FindRentalRecordOwnQuery query) {
        //
        query.validate();
        String rentalRecordId = query.getRentalRecordId();
        RentalRecord response = rentalOwnSeek.findRentalRecordById(rentalRecordId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-rental-record-rdos/query")
    public QueryResponse<List<RentalRecordRdo>> findRentalRecords(FindRentalRecordsOwnQuery query) {
        //
        query.validate();
        RentalStatus status = query.getStatus();
        String searchKeyword = query.getSearchKeyword();
        Offset offset = query.getOffset();
        Page<RentalRecordRdo> response = rentalOwnSeek.findRentalRecords(status, searchKeyword, offset);
        query.setResponse(response);
        return query.getResponse();
    }

    @Override
    @PostMapping("/find-item-condition-check/query")
    public QueryResponse<ItemConditionCheck> findItemConditionCheck(@RequestBody FindItemConditionCheckOwnQuery query) {
        //
        query.validate();
        String itemConditionCheckId = query.getItemConditionCheckId();
        ItemConditionCheck response = rentalOwnSeek.findItemConditionCheckById(itemConditionCheckId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-item-condition-photo/query")
    public QueryResponse<ItemConditionPhoto> findItemConditionPhoto(@RequestBody FindItemConditionPhotoOwnQuery query) {
        //
        query.validate();
        String itemConditionPhotoId = query.getItemConditionPhotoId();
        ItemConditionPhoto response = rentalOwnSeek.findItemConditionPhotoById(itemConditionPhotoId);
        return new QueryResponse<>(response);
    }
}