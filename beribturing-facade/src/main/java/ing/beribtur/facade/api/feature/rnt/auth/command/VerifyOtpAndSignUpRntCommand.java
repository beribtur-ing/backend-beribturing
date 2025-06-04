package ing.beribtur.facade.api.feature.rnt.auth.command;

import ing.beribtur.aggregate.user.entity.vo.Profile;
import ing.beribtur.feature.shared.util.AuthUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@RequiredArgsConstructor
@Getter
@Setter
public class VerifyOtpAndSignUpRntCommand {
    //
    private String phoneNumber;
    private String otp;
    private String password;
    private String name;
    private Profile profile;

    public void validate() {
        //
        AuthUtil.phoneNumberValidation(phoneNumber);
        Assert.hasText(otp, "otp must not be empty");
        AuthUtil.passwordValidation(password);
        Assert.hasText(name, "name must not be empty");
        Assert.notNull(profile, "profile must not be null");
        AuthUtil.emailValidation(getProfile().getEmail());
        Assert.hasText(profile.getAddress(), "profile.address must not be empty");
        Assert.notNull(profile.getLocation(), "profile.location must not be null");
        Assert.notNull(profile.getLocation().getLatitude(), "profile.location.latitude must not be null");
        Assert.notNull(profile.getLocation().getLongitude(), "profile.location.longitude must not be null");
    }
}
