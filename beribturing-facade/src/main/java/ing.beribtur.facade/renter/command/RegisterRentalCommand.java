package ing.beribtur.facade.renter.command;

//import ing.beribtur.aggregate.renter.entity.sdo.RentalCdo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import static ing.beribtur.accent.util.AssertUtil.assertAllFieldsNonNull;

@RequiredArgsConstructor
@Getter
@Setter
public class RegisterRentalCommand {
    //
//    private RentalCdo rentalCdo;

    public void validate() {
        //
//        Assert.notNull(rentalCdo, "rentalCdo must not be null");
//        assertAllFieldsNonNull(rentalCdo);
    }
}
