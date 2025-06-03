package ing.beribtur.aggregate.rental.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.util.JsonUtil;
import ing.beribtur.aggregate.item.entity.ProductVariant;
import ing.beribtur.aggregate.rental.entity.vo.Period;
import ing.beribtur.aggregate.rental.entity.vo.ReservationStatus;
import ing.beribtur.aggregate.user.entity.Lendee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation extends DomainEntity {
    //
    private String productVariantId;      // Reference to Product Variant
    private String requesterId;           // Reference to the Lendee who made the reservation
    private Period period;              // The period for which the product is reserved
    private ReservationStatus status;
    private String note;

    // Domain relationships
    private transient ProductVariant productVariant;    // The product variant being reserved
    private transient Lendee requester;                 // The Lendee who made the reservation

    @Override
    protected void modifyAttributes(NameValueList nameValues) {
        for (NameValue nameValue : nameValues.list()) {
            String value = nameValue.getValue();
            switch (nameValue.getName().trim()) {
                case "productVariantId" -> this.productVariantId = value;
                case "requesterId" -> this.requesterId = value;
                case "period" -> this.period = JsonUtil.fromJson(value, Period.class);
                case "status" -> this.status = ReservationStatus.valueOf(value);
                case "note" -> this.note = value;
                default -> throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }
}
