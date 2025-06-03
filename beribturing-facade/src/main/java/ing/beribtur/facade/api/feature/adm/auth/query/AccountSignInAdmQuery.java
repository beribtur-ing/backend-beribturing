package ing.beribtur.facade.api.feature.adm.auth.query;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@RequiredArgsConstructor
@Getter
@Setter
public class AccountSignInAdmQuery {
    //
    private String phoneNumber;
    private String password;

    public void validate() {
        //
        Assert.hasText(phoneNumber, "phoneNumber must not be null");
        Assert.isTrue(phoneNumber.matches("^998\\d{9}$"), "phoneNumber must be a valid number");
        Assert.hasText(password, "password must not be null");
        Assert.isTrue(password.length() >= 6, "password must be at least 6 characters long");
        //contains at least one digit, one lowercase letter, one uppercase letter, and one special character
        Assert.isTrue(password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{6,}$"),
                      "password must contain at least one digit, one lowercase letter, one uppercase letter, and one special character");
    }
}
