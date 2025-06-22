package ing.beribtur.facade.api.feature.adm.payment.command;

import ing.beribtur.accent.message.CommandRequest;
import ing.beribtur.aggregate.payment.entity.sdo.DiscountCdo;
import ing.beribtur.aggregate.payment.entity.vo.DiscountScope;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class RegisterDiscountAdmCommand extends CommandRequest<String> {
    //
    private DiscountCdo discountCdo;

    public void validate() {
        //
        Assert.hasText(discountCdo.getName(), "Name is required.");
        Assert.notNull(discountCdo.getType(), "Type is required.");
        Assert.notNull(discountCdo.getAmount(), "Amount is required.");
        Assert.notNull(discountCdo.getScope(), "Scope is required.");
        if(!discountCdo.getScope().equals(DiscountScope.Global)) {
            Assert.hasText(discountCdo.getTargetId(), "Target is required for scope");
        }
        Assert.notNull(discountCdo.getStartDate(), "Start date is required.");
        Assert.notNull(discountCdo.getEndDate(), "End date is required.");
        Assert.isTrue(discountCdo.getStartDate().isBefore(discountCdo.getEndDate()), "Start date must be before end date.");
    }
}
