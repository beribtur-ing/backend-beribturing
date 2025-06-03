package ing.beribtur.facade.api.auth.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@RequiredArgsConstructor
@Getter
@Setter
public class SendOtpCommand {
    //
    private String phoneNumber;

    public void validate() {
        //
        Assert.notNull(phoneNumber, "phoneNumber must not be null");
        Assert.isTrue(phoneNumber.matches("^998\\d{9}$"), "phoneNumber must be a valid number");
    }
}
