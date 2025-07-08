package ing.beribtur.facade.api.feature.adm.systemsettings.command;

import ing.beribtur.accent.message.CommandRequest;
import ing.beribtur.aggregate.account.entity.sdo.SystemSettingsCdo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterSystemSettingsAdmCommand extends CommandRequest<String> {
    //
    private SystemSettingsCdo systemSettingsCdo;

    public void validate() {
        //
        Assert.hasText(systemSettingsCdo.getPlatformName(), "Platform name is required.");
        Assert.hasText(systemSettingsCdo.getSupportEmail(), "Support email is required.");
        Assert.hasText(systemSettingsCdo.getPlatformDescription(), "Platform description is required.");
        Assert.notNull(systemSettingsCdo.getPaymentSettings(), "Payment settings are required.");
        Assert.notNull(systemSettingsCdo.getNotificationSettings(), "Notification settings are required.");
        Assert.notNull(systemSettingsCdo.getSecuritySettings(), "Security settings are required.");
    }
}

