package ing.beribtur.facade.api.feature.adm.rental.query;

import ing.beribtur.accent.message.OffsetQueryRequest;
import ing.beribtur.feature.shared.sdo.RentalRecordRdo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.List;

@Getter
@Setter
public class FindRentalRecordsAdmQuery extends OffsetQueryRequest<List<RentalRecordRdo>> {
    //
    private String lendeeId;

    public void validate() {
        Assert.hasText(lendeeId, "Lendee id cannot be empty");
    }
}
