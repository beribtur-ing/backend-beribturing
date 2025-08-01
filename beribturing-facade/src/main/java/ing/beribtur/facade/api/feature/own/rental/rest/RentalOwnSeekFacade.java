package ing.beribtur.facade.api.feature.own.rental.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.rental.entity.ItemConditionCheck;
import ing.beribtur.aggregate.rental.entity.ItemConditionPhoto;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.facade.api.feature.own.rental.query.*;
import ing.beribtur.feature.shared.sdo.RentalRecordRdo;
import ing.beribtur.feature.shared.sdo.ReservationDetailRdo;
import ing.beribtur.feature.shared.sdo.ReservationRdo;

import java.util.List;

public interface RentalOwnSeekFacade {
    //
    QueryResponse<List<ReservationRdo>> findReservationRdos(FindReservationRdosOwnQuery query);
    QueryResponse<ReservationDetailRdo> findReservationDetail(FindReservationDetailOwnQuery query) throws Exception;
    QueryResponse<RentalRecord> findRentalRecord(FindRentalRecordOwnQuery query);
    QueryResponse<List<RentalRecordRdo>> findRentalRecords(FindRentalRecordsOwnQuery query);
    QueryResponse<ItemConditionCheck> findItemConditionCheck(FindItemConditionCheckOwnQuery query);
    QueryResponse<ItemConditionPhoto> findItemConditionPhoto(FindItemConditionPhotoOwnQuery query);
}
