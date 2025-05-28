package ing.beribtur.aggregate.admin.store;


import ing.beribtur.aggregate.admin.entity.SubscriptionPlan;

import java.util.List;

public interface SubscriptionPlanStore {
    //
    SubscriptionPlan create(SubscriptionPlan SubscriptionPlan);
    SubscriptionPlan retrieve(Long id);
    List<SubscriptionPlan> retrieveAll(List<Long> ids);
    SubscriptionPlan update(SubscriptionPlan SubscriptionPlan);
    void delete(Long id);
}
