package ing.beribtur.aggregate.payment.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.payment.entity.vo.DiscountScope;
import ing.beribtur.aggregate.payment.entity.vo.DiscountType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class DiscountCdo extends CreationDataObject {
    //
    private String name;                      // e.g., "Summer Promo", "VIP Discount"
    private DiscountType type;                // PERCENTAGE or FIXED_AMOUNT
    private BigDecimal amount;                // 10% or $5
    private DiscountScope scope;              // PRODUCT, VARIANT, CATEGORY, USER, GLOBAL
    private String targetId;                    // UUID of Product, Variant, Category, etc.
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean active;
}
