package ing.beribtur.facade.api.auth.command;

import ing.beribtur.aggregate.user.entity.vo.LenderType;
import ing.beribtur.aggregate.user.entity.vo.Profile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@RequiredArgsConstructor
@Getter
@Setter
public class VerifyOtpAndSignUpLenderCommand {
    //
    private String phoneNumber;
    private String otp;
    private String password;
    private String name;
    private Profile profile;
    private LenderType lenderType;

    public void validate() {
        //
        Assert.hasText(phoneNumber, "phoneNumber must not be empty");
        Assert.isTrue(phoneNumber.matches("^998\\d{9}$"), "phoneNumber must be a valid number");
        Assert.hasText(otp, "otp must not be empty");
        Assert.hasText(password, "password must not be empty");
        Assert.hasText(name, "name must not be empty");
        Assert.notNull(profile, "profile must not be null");
        Assert.hasText(profile.getEmail(), "email must not be empty");
        Assert.isTrue(profile.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"), "email must be a valid email address");
        Assert.hasText(profile.getAddress(), "profile.address must not be empty");
        Assert.notNull(profile.getLocation(), "profile.location must not be null");
        Assert.notNull(profile.getLocation().getLatitude(), "profile.location.latitude must not be null");
        Assert.notNull(profile.getLocation().getLongitude(), "profile.location.longitude must not be null");
        Assert.notNull(lenderType, "lenderType must not be null");
    }
}
