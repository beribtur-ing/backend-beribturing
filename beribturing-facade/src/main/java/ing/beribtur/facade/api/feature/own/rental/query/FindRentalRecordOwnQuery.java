package ing.beribtur.facade.api.feature.own.rental.query;

import ing.beribtur.accent.message.QueryRequest;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class FindRentalRecordOwnQuery extends QueryRequest<RentalRecord> {
    //
    private String rentalRecordId;

    public void validate() {
        //
        Assert.hasText(rentalRecordId, "'rentalRecordId' is required.");
    }
}