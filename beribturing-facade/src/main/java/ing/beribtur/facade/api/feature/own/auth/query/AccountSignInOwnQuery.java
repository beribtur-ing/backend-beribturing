package ing.beribtur.facade.api.feature.own.auth.query;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@RequiredArgsConstructor
@Getter
@Setter
public class AccountSignInOwnQuery {
    //
    private String phoneNumber;
    private String password;

    public void validate() {
        //
        Assert.hasText(phoneNumber, "phoneNumber must not be null");
        Assert.hasText(password, "password must not be null");
    }
}
