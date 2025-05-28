package ing.beribtur.aggregate.admin.store;


import ing.beribtur.aggregate.admin.entity.UserSubscription;

import java.util.List;

public interface UserSubscriptionStore {
    //
    UserSubscription create(UserSubscription UserSubscription);
    UserSubscription retrieve(Long id);
    List<UserSubscription> retrieveAll(List<Long> ids);
    UserSubscription update(UserSubscription UserSubscription);
    void delete(Long id);
}
