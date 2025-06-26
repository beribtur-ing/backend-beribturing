package ing.beribtur.facade.api.feature.own.rental.query;

import ing.beribtur.accent.message.OffsetQueryRequest;
import ing.beribtur.aggregate.rental.entity.vo.RentalStatus;
import ing.beribtur.feature.shared.sdo.RentalRecordRdo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class FindRentalRecordsOwnQuery extends OffsetQueryRequest<List<RentalRecordRdo>> {
    //
    private RentalStatus status;
    private String searchKeyword;

    public void validate() {
        //
        if (status != null) {
            Assert.isTrue(Arrays.stream(RentalStatus.values()).anyMatch(s -> s.equals(status)), "'status' is invalid");
        }
    }
}