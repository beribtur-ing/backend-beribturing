package ing.beribtur.aggregate.admin.entity;

import java.math.BigDecimal;

public class SubscriptionPlan {
    private Long id;

    private String name;
    private String description;

    private BigDecimal monthlyFee;

    private int maxListings;
    private int maxConcurrentRentals;

    private boolean active;
}
