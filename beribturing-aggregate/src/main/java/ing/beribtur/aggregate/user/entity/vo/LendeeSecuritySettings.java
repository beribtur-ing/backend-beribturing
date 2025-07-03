package ing.beribtur.aggregate.user.entity.vo;

import ing.beribtur.accent.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LendeeSecuritySettings implements ValueObject {
    private boolean twoFactorAuthentication;
    private boolean loginAlertsForNewDevices;
    private int sessionTimeoutMinutes;

    public static LendeeSecuritySettings getDefaultSettings() {
        return new LendeeSecuritySettings(
            false,  // Two-factor authentication disabled by default
            true,   // Login alerts enabled by default for security
            30      // 30 minutes session timeout by default
        );
    }
}