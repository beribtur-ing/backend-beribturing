package ing.beribtur.aggregate.admin.entity;

import ing.beribtur.aggregate.shared.entity.User;

import java.time.LocalDateTime;

public class UserSubscription {
    private Long id;

    private User user;

    private SubscriptionPlan plan;

    private LocalDateTime subscribedAt;
    private LocalDateTime expiresAt;

    private boolean autoRenew;
}

