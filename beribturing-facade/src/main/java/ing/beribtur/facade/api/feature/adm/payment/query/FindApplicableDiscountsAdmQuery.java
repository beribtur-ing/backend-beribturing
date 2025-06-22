package ing.beribtur.facade.api.feature.adm.payment.query;

import ing.beribtur.aggregate.payment.entity.vo.DiscountScope;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class FindApplicableDiscountsAdmQuery {
    private String targetId;
    private DiscountScope scope;

    public void validate() {
        Assert.hasText(targetId, "Target ID is required.");
        Assert.notNull(scope, "Scope is required.");
    }
} 