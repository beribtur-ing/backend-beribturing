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
        Assert.hasText(phoneNumber, "phoneNumber must not be empty");
        Assert.hasText(password, "password must not be empty");
    }
}
