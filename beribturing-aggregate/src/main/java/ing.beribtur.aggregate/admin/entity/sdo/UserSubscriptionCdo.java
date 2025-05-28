package ing.beribtur.aggregate.admin.entity.sdo;

import ing.beribtur.aggregate.admin.entity.SubscriptionPlan;
import ing.beribtur.aggregate.shared.entity.User;

import java.time.LocalDateTime;

public class UserSubscriptionCdo {

    private User user;

    private SubscriptionPlan plan;

    private LocalDateTime subscribedAt;
    private LocalDateTime expiresAt;

    private boolean autoRenew;
}

