package ing.beribtur.facade.api.feature.own.rental.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.rental.entity.ItemConditionCheck;
import ing.beribtur.aggregate.rental.entity.ItemConditionPhoto;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.facade.api.feature.own.rental.query.FindItemConditionCheckOwnQuery;
import ing.beribtur.facade.api.feature.own.rental.query.FindItemConditionPhotoOwnQuery;
import ing.beribtur.facade.api.feature.own.rental.query.FindRentalRecordOwnQuery;
import ing.beribtur.feature.own.rental.seek.RentalOwnSeek;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/owner/rental")
public class RentalOwnSeekResource implements RentalOwnSeekFacade {
    //
    private final RentalOwnSeek rentalOwnSeek;

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