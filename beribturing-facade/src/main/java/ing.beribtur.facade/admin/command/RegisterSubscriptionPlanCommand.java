package ing.beribtur.facade.admin.command;

import ing.beribtur.aggregate.admin.entity.sdo.SubscriptionPlanCdo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import static ing.beribtur.accent.util.AssertUtil.assertAllFieldsNonNull;

@RequiredArgsConstructor
@Getter
@Setter
public class RegisterSubscriptionPlanCommand {
    //
    private SubscriptionPlanCdo subscriptionPlanCdo;

    public void validate() {
        //
        Assert.notNull(subscriptionPlanCdo, "subscriptionPlanCdo must not be null");
        assertAllFieldsNonNull(subscriptionPlanCdo);
    }
}
