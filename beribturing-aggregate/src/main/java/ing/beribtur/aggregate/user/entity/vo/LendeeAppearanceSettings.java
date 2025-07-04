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
public class LendeeAppearanceSettings implements ValueObject {
    private Theme theme;
    private String defaultLanguage;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Theme {
        private boolean darkMode;
        private boolean compactView;
    }

    public static LendeeAppearanceSettings getDefaultSettings() {
        return new LendeeAppearanceSettings(
            new Theme(false, false),
            "en"
        );
    }
}