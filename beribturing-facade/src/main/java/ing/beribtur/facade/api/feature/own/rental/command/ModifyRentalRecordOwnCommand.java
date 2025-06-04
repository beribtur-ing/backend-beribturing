package ing.beribtur.facade.api.feature.own.rental.command;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.CommandRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class ModifyRentalRecordOwnCommand extends CommandRequest<String> {
    //
    private String rentalRecordId;
    private NameValueList nameValueList;

    public void validate() {
        //
        Assert.hasText(rentalRecordId, "'rentalRecordId' is required.");
        Assert.notNull(nameValueList, "'nameValueList' is required.");
    }
}
