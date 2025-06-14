package ing.beribtur.facade.api.feature.own.item.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class FindProductOwnQuery {
    //
    private String productId;

    public void validate() {
        //
        Assert.hasText(productId, "'productId' is required.");
    }
}