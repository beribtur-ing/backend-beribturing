package ing.beribtur.facade.api.feature.item.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class FindProductQuery {
    //
    private String productId;

    public void validate() {
        //
        Assert.hasText(productId, "'productId' is required.");
    }
} 