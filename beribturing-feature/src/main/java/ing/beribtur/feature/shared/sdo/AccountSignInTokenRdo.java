package ing.beribtur.feature.shared.sdo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountSignInTokenRdo {
    private String accessToken;
    private String refreshToken;
}
