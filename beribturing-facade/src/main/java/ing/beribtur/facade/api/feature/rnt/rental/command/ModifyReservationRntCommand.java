package ing.beribtur.facade.api.feature.rnt.rental.command;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.CommandRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class ModifyReservationRntCommand extends CommandRequest<String> {
    //
    private String reservationId;
    private NameValueList nameValueList;

    public void validate() {
        //
        Assert.hasText(reservationId, "'reservationId' is required.");
        Assert.notNull(nameValueList, "'nameValueList' is required.");
    }
}
