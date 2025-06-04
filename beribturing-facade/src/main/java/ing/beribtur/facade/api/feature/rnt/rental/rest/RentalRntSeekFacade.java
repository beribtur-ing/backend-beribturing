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

public interface RentalRntSeekFacade {
    //
    QueryResponse<RentalRecord> findRentalRecord(FindRentalRecordRntQuery query);
    QueryResponse<Reservation> findReservation(FindReservationRntQuery query);
    QueryResponse<ItemConditionCheck> findItemConditionCheck(FindItemConditionCheckRntQuery query);
    QueryResponse<ItemConditionPhoto> findItemConditionPhoto(FindItemConditionPhotoRntQuery query);
}