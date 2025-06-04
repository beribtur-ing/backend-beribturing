package ing.beribtur.facade.api.feature.rnt.rental.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class FindRentalRecordRntQuery {
    //
    private String rentalRecordId;

    public void validate() {
        //
        Assert.hasText(rentalRecordId, "'rentalRecordId' is required.");
    }
}