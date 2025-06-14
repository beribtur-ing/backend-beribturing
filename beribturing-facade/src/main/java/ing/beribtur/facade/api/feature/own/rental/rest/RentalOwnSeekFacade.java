package ing.beribtur.facade.api.feature.own.rental.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.rental.entity.ItemConditionCheck;
import ing.beribtur.aggregate.rental.entity.ItemConditionPhoto;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.rental.entity.Reservation;
import ing.beribtur.facade.api.feature.own.rental.query.FindItemConditionCheckOwnQuery;
import ing.beribtur.facade.api.feature.own.rental.query.FindItemConditionPhotoOwnQuery;
import ing.beribtur.facade.api.feature.own.rental.query.FindRentalRecordOwnQuery;
import ing.beribtur.facade.api.feature.own.rental.query.FindReservationsOwnQuery;

import java.util.List;

public interface RentalOwnSeekFacade {
    //
    QueryResponse<RentalRecord> findRentalRecord(FindRentalRecordOwnQuery query);
    QueryResponse<List<Reservation>> findReservations(FindReservationsOwnQuery query);
    QueryResponse<ItemConditionCheck> findItemConditionCheck(FindItemConditionCheckOwnQuery query);
    QueryResponse<ItemConditionPhoto> findItemConditionPhoto(FindItemConditionPhotoOwnQuery query);
}