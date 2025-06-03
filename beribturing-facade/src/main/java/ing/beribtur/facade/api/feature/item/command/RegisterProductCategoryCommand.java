package ing.beribtur.facade.api.feature.item.command;

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
public class RegisterProductCategoryCommand extends CommandRequest<String> {
    //
    private ProductCategoryCdo productCategoryCdo;

    public void validate() {
        //
        Assert.notNull(productCategoryCdo, "'categoryCdo' is required.");
        Assert.notNull(productCategoryCdo.getParentId(), "'parentId' is required.");
        Assert.notNull(productCategoryCdo.getName(), "'name' is required.");
        Assert.notNull(productCategoryCdo.getDescription(), "'description' is required.");
        Assert.notNull(productCategoryCdo.getIconUrl(), "'iconUrl' is required.");
    }

}
