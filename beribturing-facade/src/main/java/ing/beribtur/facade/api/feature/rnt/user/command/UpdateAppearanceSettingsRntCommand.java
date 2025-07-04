package ing.beribtur.facade.api.feature.rnt.user.command;

import ing.beribtur.accent.message.CommandRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class UpdateAppearanceSettingsRntCommand extends CommandRequest<String> {
    private boolean darkMode;
    private boolean compactView;
    private String defaultLanguage;

    public void validate() {
        Assert.hasText(defaultLanguage, "Default language is required");
        Assert.isTrue(defaultLanguage.length() <= 10, "Default language code must be 10 characters or less");
    }
}