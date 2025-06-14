package ing.beribtur.facade.api.feature.adm.item.command;

import ing.beribtur.accent.message.CommandRequest;
import ing.beribtur.feature.adm.item.sdo.ProductCategoryRegCdo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterProductCategoryAdmCommand extends CommandRequest<String> {
    //
    private ProductCategoryRegCdo productCategoryRegCdo;

    public void validate() {
        //
        Assert.notNull(productCategoryRegCdo, "'productCategoryRegCdo' is required.");
    }

}
