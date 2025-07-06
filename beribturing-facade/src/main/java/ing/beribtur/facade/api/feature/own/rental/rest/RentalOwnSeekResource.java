package ing.beribtur.facade.api.feature.own.rental.rest;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.rental.entity.ItemConditionCheck;
import ing.beribtur.aggregate.rental.entity.ItemConditionPhoto;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.rental.entity.vo.RentalStatus;
import ing.beribtur.aggregate.rental.entity.vo.ReservationStatus;
import ing.beribtur.facade.api.feature.own.rental.query.*;
import ing.beribtur.feature.own.rental.seek.RentalOwnSeek;
import ing.beribtur.feature.shared.sdo.RentalRecordRdo;
import ing.beribtur.feature.shared.sdo.ReservationDetailRdo;
import ing.beribtur.feature.shared.sdo.ReservationRdo;
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
    @PostMapping("/find-reservation-rdos/query")
    public QueryResponse<List<ReservationRdo>> findReservationRdos(@RequestBody FindReservationRdosOwnQuery query) {
        //
        query.validate();
        ReservationStatus status = query.getStatus();
        Offset offset = query.getOffset();
        Page<ReservationRdo> response = rentalOwnSeek.findReservationRdos(status, offset);
        query.setResponse(response);
        return query.getResponse();
    }

    @Override
    @PostMapping("/find-reservation-detail/query")
    public QueryResponse<ReservationDetailRdo> findReservationDetail(@RequestBody FindReservationDetailOwnQuery query) throws Exception {
        //
        query.validate();
        String reservationId = query.getReservationId();
        ReservationDetailRdo response = rentalOwnSeek.findReservationDetail(reservationId);
        query.setResponse(response);
        return query.getResponse();
    }

    @Override
    @PostMapping("/find-rental-record/query")
    public QueryResponse<RentalRecord> findRentalRecord(@RequestBody FindRentalRecordOwnQuery query) {
        //
        query.validate();
        String rentalRecordId = query.getRentalRecordId();
        RentalRecord response = rentalOwnSeek.findRentalRecordById(rentalRecordId);
        query.setResponse(response);
        return query.getResponse();
    }

    @Override
    @PostMapping("/find-rental-record-rdos/query")
    public QueryResponse<List<RentalRecordRdo>> findRentalRecords(@RequestBody FindRentalRecordsOwnQuery query) {
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
        query.setResponse(response);
        return query.getResponse();
    }

    @Override
    @PostMapping("/find-item-condition-photo/query")
    public QueryResponse<ItemConditionPhoto> findItemConditionPhoto(@RequestBody FindItemConditionPhotoOwnQuery query) {
        //
        query.validate();
        String itemConditionPhotoId = query.getItemConditionPhotoId();
        ItemConditionPhoto response = rentalOwnSeek.findItemConditionPhotoById(itemConditionPhotoId);
        query.setResponse(response);
        return query.getResponse();
    }
}
