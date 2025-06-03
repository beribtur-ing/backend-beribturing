package ing.beribtur.facade.api.auth.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
@NoArgsConstructor
public class ResetPasswordCommand {
    //
    private String phoneNumber;
    private String newPassword;
    private String otp;

    public void validate() {
        //
        Assert.notNull(phoneNumber, "phoneNumber must not be null");
        Assert.isTrue(phoneNumber.matches("^998\\d{9}$"), "phoneNumber must be a valid number");
        Assert.notNull(newPassword, "newPassword must not be null");
        Assert.notNull(otp, "otp must not be null");
    }
}
