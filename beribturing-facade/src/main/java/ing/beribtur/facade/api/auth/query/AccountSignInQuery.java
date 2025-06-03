package ing.beribtur.facade.api.auth.query;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@RequiredArgsConstructor
@Getter
@Setter
public class AccountSignInQuery {
    //
    private String phoneNumber;
    private String password;

    public void validate() {
        //
        Assert.notNull(phoneNumber, "phoneNumber must not be null");
        Assert.notNull(password, "password must not be null");
    }
}
