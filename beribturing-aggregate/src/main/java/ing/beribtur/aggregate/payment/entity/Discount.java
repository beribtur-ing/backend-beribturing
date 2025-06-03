package ing.beribtur.aggregate.payment.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.payment.entity.vo.DiscountScope;
import ing.beribtur.aggregate.payment.entity.vo.DiscountType;
import ing.beribtur.aggregate.payment.entity.vo.Discountable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

    @Override
    protected void modifyAttributes(NameValueList nameValues) {
        for (NameValue nameValue : nameValues.list()) {
            String value = nameValue.getValue();
            switch (nameValue.getName().trim()) {
                case "name" -> this.name = value;
                case "type" -> this.type = DiscountType.valueOf(value);
                case "amount" -> this.amount = new BigDecimal(value);
                case "scope" -> this.scope = DiscountScope.valueOf(value);
                case "targetId" -> this.targetId = value;
                case "startDate" -> this.startDate = LocalDateTime.parse(value);
                case "endDate" -> this.endDate = LocalDateTime.parse(value);
                case "active" -> this.active = Boolean.parseBoolean(value);
                default -> throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }
}

