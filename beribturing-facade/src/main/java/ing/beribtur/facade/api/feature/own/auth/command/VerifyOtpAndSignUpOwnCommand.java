package ing.beribtur.facade.api.feature.own.auth.command;

import ing.beribtur.aggregate.user.entity.vo.LenderType;
import ing.beribtur.aggregate.user.entity.vo.Profile;
import ing.beribtur.feature.shared.util.AuthUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Getter
@Setter
public class VerifyOtpAndSignUpOwnCommand {
    //
    private String phoneNumber;
    private String otp;
    private String password;
    private String name;
    private Profile profile;
    private LenderType lenderType;

    public void validate() {
        //
        AuthUtil.phoneNumberValidation(phoneNumber);
        Assert.hasText(otp, "otp must not be empty");
        AuthUtil.passwordValidation(password);
        Assert.hasText(name, "name must not be empty");
        if (profile != null && StringUtils.hasText(profile.getEmail())) {
            AuthUtil.emailValidation(profile.getEmail());
        }
//        Assert.hasText(profile.getAddress(), "profile.address must not be empty");
//        Assert.notNull(profile.getLocation(), "profile.location must not be null");
//        Assert.notNull(profile.getLocation().getLatitude(), "profile.location.latitude must not be null");
//        Assert.notNull(profile.getLocation().getLongitude(), "profile.location.longitude must not be null");
        Assert.notNull(lenderType, "lenderType must not be null");
    }
}
