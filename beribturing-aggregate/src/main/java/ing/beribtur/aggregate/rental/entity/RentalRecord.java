package ing.beribtur.aggregate.rental.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.util.JsonUtil;
import ing.beribtur.aggregate.item.entity.ProductVariant;
import ing.beribtur.aggregate.payment.entity.Discount;
import ing.beribtur.aggregate.payment.entity.RentalDeposit;
import ing.beribtur.aggregate.payment.entity.vo.Currency;
import ing.beribtur.aggregate.rental.entity.sdo.RentalRecordCdo;
import ing.beribtur.aggregate.rental.entity.vo.Period;
import ing.beribtur.aggregate.rental.entity.vo.RentalStatus;
import ing.beribtur.aggregate.user.entity.Lendee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RentalRecord extends DomainEntity {
    //
    private Period period;
    private LocalDateTime rentedAt;       // The date and time when the product was rented
    private LocalDateTime returnedAt;     // The date when the product was returned
    private LocalDateTime cancelledAt;    // The date when the rental was canceled
    private String productVariantId;              // Reference to Product Variant
    private RentalStatus status;
    private String lendeeId;                      // Reference to the Reservation that this rental is associated with
    private Currency fee;
    private String discountId;                    // Reference to any discount applied to the rental
    private String depositId;                     // Rental Deposit id

    // Domain relationships
    private transient ProductVariant productVariant;    // The product variant being rented
    private transient Reservation reservation;          // The reservation that this rental is associated with
    private transient Lendee lendee;                    // The Lendee who made the rental
    private transient Discount discount;
    private transient RentalDeposit deposit;            // The deposit associated with this rental

    private transient ItemConditionCheck beforeRented;
    private transient ItemConditionCheck afterReturned;

    public RentalRecord(RentalRecordCdo rentalRecordCdo) {
        //
        super(rentalRecordCdo.genId());
        BeanUtils.copyProperties(rentalRecordCdo, this);
    }

    public static String genId(String reservationId) {
        //
        return reservationId;
    }

    @Override
    protected void modifyAttributes(NameValueList nameValues) {
        for (NameValue nameValue : nameValues.list()) {
            String value = nameValue.getValue();
            switch (nameValue.getName().trim()) {
                case "period" -> this.period = JsonUtil.fromJson(value, Period.class);
                case "rentedAt" -> this.rentedAt = LocalDateTime.parse(value);
                case "returnedAt" -> this.returnedAt = LocalDateTime.parse(value);
                case "cancelledAt" -> this.cancelledAt = LocalDateTime.parse(value);
                case "status" -> this.status = RentalStatus.valueOf(value);
                case "fee" -> this.fee = JsonUtil.fromJson(value, Currency.class);
                default -> throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }
}
