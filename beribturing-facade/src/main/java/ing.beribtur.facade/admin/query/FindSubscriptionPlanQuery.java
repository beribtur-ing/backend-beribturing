package ing.beribtur.facade.admin.query;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
@RequiredArgsConstructor
public class FindSubscriptionPlanQuery {
    //
    private Long subscriptionPlanId;

    public void validate() {
        //
        Assert.notNull(subscriptionPlanId, "subscriptionPlanId must not be null");
    }
}
