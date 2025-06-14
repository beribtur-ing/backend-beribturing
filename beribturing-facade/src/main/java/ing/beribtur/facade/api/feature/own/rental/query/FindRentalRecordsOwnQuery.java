package ing.beribtur.facade.api.feature.own.rental.query;

import ing.beribtur.aggregate.rental.entity.vo.RentalStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.Arrays;

@Getter
@Setter
public class FindRentalRecordsOwnQuery {
    //
    private String ownerId;
    private RentalStatus status;

    public void validate() {
        //
        Assert.hasText(ownerId, "'ownerId' must not be empty");
        Assert.isTrue(ownerId.trim().equals(ownerId), "'ownerId' must not contain leading or trailing spaces");
        if (status != null) {
            Assert.isTrue(Arrays.stream(RentalStatus.values()).anyMatch(s -> s.equals(status)), "'status' is invalid");
        }
    }
}