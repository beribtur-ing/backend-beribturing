package ing.beribtur.feature.shared.sdo;

import ing.beribtur.aggregate.user.entity.vo.LendeeSecuritySettings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LendeeSecuritySettingsRdo {
    private boolean twoFactorAuthentication;
    private boolean loginAlertsForNewDevices;
    private int sessionTimeoutMinutes;

    public static LendeeSecuritySettingsRdo from(LendeeSecuritySettings securitySettings) {
        if (securitySettings == null) {
            return null;
        }
        
        return new LendeeSecuritySettingsRdo(
            securitySettings.isTwoFactorAuthentication(),
            securitySettings.isLoginAlertsForNewDevices(),
            securitySettings.getSessionTimeoutMinutes()
        );
    }
}