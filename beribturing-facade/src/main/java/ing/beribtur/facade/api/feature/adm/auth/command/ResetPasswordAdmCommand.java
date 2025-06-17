package ing.beribtur.facade.api.feature.adm.auth.command;

import ing.beribtur.feature.shared.util.AuthUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
@NoArgsConstructor
public class ResetPasswordAdmCommand {
    //
    private String phoneNumber;
    private String newPassword;
    private String otp;

    public void validate() {
        //
        AuthUtil.phoneNumberValidation(phoneNumber);
        AuthUtil.passwordValidation(newPassword);
        Assert.notNull(otp, "otp must not be null");
    }
}
