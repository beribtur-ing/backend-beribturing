package ing.beribtur.facade.api.feature.rnt.auth.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenRntCommand {
    private String refreshToken;

    public void validate() {
        Assert.hasText(refreshToken, "RefreshToken is required");
    }
}