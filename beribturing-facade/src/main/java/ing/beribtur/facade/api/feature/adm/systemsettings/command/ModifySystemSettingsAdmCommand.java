package ing.beribtur.facade.api.feature.adm.systemsettings.command;

import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.CommandRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class ModifySystemSettingsAdmCommand extends CommandRequest<String> {
    //
    private NameValueList nameValueList;

    public void validate() {
        //
        //
        Assert.notNull(nameValueList, "'nameValueList' is required");
        Assert.isTrue(!nameValueList.isEmpty(), "'nameValueList' must not be empty");
        for (NameValue nameValue : nameValueList.getNameValues()) {
            Assert.hasText(nameValue.getName(), "'name' in 'nameValueList' must not be empty");
            Assert.hasText(nameValue.getValue(), "'value' in 'nameValueList' must not be empty");
            Assert.isTrue(nameValue.getName().trim().equals(nameValue.getName()), "'name' in 'nameValueList' must not have leading or trailing spaces");
            Assert.isTrue(nameValue.getValue().trim().equals(nameValue.getValue()), "'value' in 'nameValueList' must not have leading or trailing spaces");
        }
    }
}
