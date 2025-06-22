package ing.beribtur.facade.api.feature.adm.payment.query;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.payment.entity.vo.DiscountScope;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FindDiscountsAdmQuery {
    private List<String> discountIds;
    private Offset offset;
    private Boolean active;
    private DiscountScope scope;
    private String targetId;

    public void validate() {
        // At least one filter should be provided
        if (discountIds == null && offset == null && active == null && scope == null && targetId == null) {
            throw new IllegalArgumentException("At least one filter must be provided.");
        }
    }
} 