package ing.beribtur.facade.api.feature.rnt.user.command;

import ing.beribtur.accent.message.CommandRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class UpdateSecuritySettingsRntCommand extends CommandRequest<String> {
    private boolean twoFactorAuthentication;
    private boolean loginAlertsForNewDevices;
    private int sessionTimeoutMinutes;

    public void validate() {
        Assert.isTrue(sessionTimeoutMinutes > 0, "Session timeout must be greater than 0");
        Assert.isTrue(sessionTimeoutMinutes <= 1440, "Session timeout cannot exceed 24 hours (1440 minutes)");
    }
}