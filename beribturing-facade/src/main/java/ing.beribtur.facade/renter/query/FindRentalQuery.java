package ing.beribtur.facade.renter.query;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
@RequiredArgsConstructor
public class FindRentalQuery {
    //
    private Long rentalId;

    public void validate() {
        //
        Assert.notNull(rentalId, "rentalId must not be null");
    }
}
