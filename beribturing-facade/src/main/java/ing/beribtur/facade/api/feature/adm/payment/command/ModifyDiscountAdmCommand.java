package ing.beribtur.facade.api.feature.adm.payment.command;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.CommandRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class ModifyDiscountAdmCommand extends CommandRequest<Void> {
    private String discountId;
    private NameValueList nameValues;

    public void validate() {
        Assert.hasText(discountId, "Discount ID is required.");
        Assert.notNull(nameValues, "Name values are required.");
    }
} 