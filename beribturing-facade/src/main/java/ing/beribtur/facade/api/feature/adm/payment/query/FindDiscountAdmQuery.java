package ing.beribtur.facade.api.feature.adm.payment.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class FindDiscountAdmQuery {
    private String discountId;

    public void validate() {
        Assert.hasText(discountId, "Discount ID is required.");
    }
} 