package ing.beribtur.aggregate.admin.entity.sdo;

import java.math.BigDecimal;

public class SubscriptionPlanCdo {

    private String name;
    private String description;

    private BigDecimal monthlyFee;

    private int maxListings;
    private int maxConcurrentRentals;

    private boolean active;
}
