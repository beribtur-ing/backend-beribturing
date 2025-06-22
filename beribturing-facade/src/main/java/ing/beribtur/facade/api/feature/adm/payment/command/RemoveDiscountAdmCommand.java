package ing.beribtur.facade.api.feature.adm.payment.command;

import ing.beribtur.accent.message.CommandRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class RemoveDiscountAdmCommand extends CommandRequest<Void> {
    private String discountId;

    public void validate() {
        Assert.hasText(discountId, "Discount ID is required.");
    }
} 