package ing.beribtur.facade.api.feature.adm.item.command;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.CommandRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModifyProductCategoryAdmCommand extends CommandRequest<String> {
    //
    private String categoryId;
    private NameValueList nameValueList;

    public void validate() {
        //
        Assert.hasText(categoryId, "'categoryId' is required.");
        Assert.notNull(nameValueList, "'nameValueList' is required.");
    }
}
