package ing.beribtur.feature.shared.sdo;

import ing.beribtur.aggregate.user.entity.vo.LendeeNotificationPreferences;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LendeeAllSettingsRdo {
    private LendeePrivacySettingsRdo privacySettings;
    private LendeeSecuritySettingsRdo securitySettings;
    private LendeeAppearanceSettingsRdo appearanceSettings;
    private LendeeNotificationPreferences notificationPreferences;
}