package ing.beribtur.facade.api.feature.own.user.command;

import ing.beribtur.accent.message.CommandRequest;
import ing.beribtur.aggregate.user.entity.vo.LendeePrivacySettings;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class UpdatePrivacySettingsOwnCommand extends CommandRequest<String> {
    private LendeePrivacySettings.ProfileVisibility profileVisibility;
    private LendeePrivacySettings.DataAndLocation dataAndLocation;

    public void validate() {
        Assert.notNull(profileVisibility, "Profile visibility settings are required");
        Assert.notNull(dataAndLocation, "Data and location settings are required");
    }
}