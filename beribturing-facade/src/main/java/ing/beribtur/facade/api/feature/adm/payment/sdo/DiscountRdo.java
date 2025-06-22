package ing.beribtur.facade.api.feature.adm.payment.sdo;

import ing.beribtur.aggregate.payment.entity.Discount;
import ing.beribtur.aggregate.payment.entity.vo.DiscountScope;
import ing.beribtur.aggregate.payment.entity.vo.DiscountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountRdo {
    private String id;
    private String name;
    private DiscountType type;
    private BigDecimal amount;
    private DiscountScope scope;
    private String targetId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean active;
    private LocalDateTime registeredOn;
    private LocalDateTime modifiedOn;

    public static DiscountRdo fromDomain(Discount discount) {
        return DiscountRdo.builder()
                .id(discount.getId())
                .name(discount.getName())
                .type(discount.getType())
                .amount(discount.getAmount())
                .scope(discount.getScope())
                .targetId(discount.getTargetId())
                .startDate(discount.getStartDate())
                .endDate(discount.getEndDate())
                .active(discount.isActive())
                .registeredOn(discount.getRegisteredOn())
                .modifiedOn(discount.getModifiedOn())
                .build();
    }
} 