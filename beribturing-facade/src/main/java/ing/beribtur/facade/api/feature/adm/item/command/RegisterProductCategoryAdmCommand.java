package ing.beribtur.facade.api.feature.adm.item.command;

import ing.beribtur.accent.message.CommandRequest;
import ing.beribtur.aggregate.item.entity.sdo.ProductCategoryCdo;
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
    private ProductCategoryCdo productCategoryCdo;

    public void validate() {
        //
        Assert.notNull(productCategoryCdo, "'categoryCdo' is required.");
    }

}
