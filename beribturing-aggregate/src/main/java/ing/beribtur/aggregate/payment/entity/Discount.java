package ing.beribtur.aggregate.payment.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.aggregate.payment.entity.vo.DiscountScope;
import ing.beribtur.aggregate.payment.entity.vo.DiscountType;
import ing.beribtur.aggregate.payment.entity.vo.Discountable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Discount extends DomainEntity {
    //
    private String name;                      // e.g., "Summer Promo", "VIP Discount"
    private DiscountType type;                // PERCENTAGE or FIXED_AMOUNT
    private BigDecimal amount;                // 10% or $5
    private DiscountScope scope;              // PRODUCT, VARIANT, CATEGORY, USER, GLOBAL
    private String targetId;                    // UUID of Product, Variant, Category, etc.
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean active;

    // Domain relationships
    private transient Discountable target;     // The entity this discount applies to, e.g., Product, Variant, User
}

