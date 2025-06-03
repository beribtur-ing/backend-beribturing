package ing.beribtur.facade.api.feature.adm.item.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class FindProductVariantAdmQuery {
    //
    private String variantId;

    public void validate() {
        //
        Assert.hasText(variantId, "'variantId' is required.");
    }
}
