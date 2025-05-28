package ing.beribtur.facade.rentalprovider.command;

import ing.beribtur.aggregate.rentalprovider.entity.sdo.RentalItemCdo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import static ing.beribtur.accent.util.AssertUtil.assertAllFieldsNonNull;

@RequiredArgsConstructor
@Getter
@Setter
public class RegisterRentalItemCommand {
    //
    private RentalItemCdo rentalItemCdo;

    public void validate() {
        //
        Assert.notNull(rentalItemCdo, "rentalItemCdo must not be null");
        assertAllFieldsNonNull(rentalItemCdo);
    }
}
