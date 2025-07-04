package ing.beribtur.feature.shared.sdo;

import ing.beribtur.aggregate.user.entity.vo.LendeeAppearanceSettings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LendeeAppearanceSettingsRdo {
    private ThemeRdo theme;
    private String defaultLanguage;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ThemeRdo {
        private boolean darkMode;
        private boolean compactView;
    }

    public static LendeeAppearanceSettingsRdo from(LendeeAppearanceSettings appearanceSettings) {
        if (appearanceSettings == null) {
            return null;
        }
        
        return new LendeeAppearanceSettingsRdo(
            new ThemeRdo(
                appearanceSettings.getTheme().isDarkMode(),
                appearanceSettings.getTheme().isCompactView()
            ),
            appearanceSettings.getDefaultLanguage()
        );
    }
}