package ing.beribtur.facade.rentalprovider.query;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
@RequiredArgsConstructor
public class FindRentalItemQuery {
    //
    private Long rentalItemId;

    public void validate() {
        //
        Assert.notNull(rentalItemId, "rentalItemId must not be null");
    }
}
