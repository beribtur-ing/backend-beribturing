package ing.beribtur.facade.api.feature.rnt.auth.command;

import ing.beribtur.feature.shared.util.AuthUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@RequiredArgsConstructor
@Getter
@Setter
public class SendOtpRntCommand {
    //
    private String phoneNumber;

    public void validate() {
        //
        AuthUtil.phoneNumberValidation(phoneNumber);
    }
}
