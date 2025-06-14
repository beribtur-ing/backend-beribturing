package ing.beribtur.aggregate.rental.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.payment.entity.vo.Currency;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.rental.entity.vo.Period;
import ing.beribtur.aggregate.rental.entity.vo.RentalStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalRecordCdo extends CreationDataObject {
    //
    private Period period;
    private LocalDateTime rentedAt;       // The date and time when the product was rented
    private LocalDateTime returnedAt;     // The date when the product was returned
    private LocalDateTime cancelledAt;    // The date when the rental was canceled
    private String productVariantId;              // Reference to Product Variant
    private RentalStatus status;
    private String lendeeId;                      // Reference to the Lendee who rented the product
    private String ownerId;                       // Reference to the Lender who owns the product
    private Currency fee;
    private String discountId;                    // Reference to any discount applied to the rental
    private String depositId;                     // Rental Deposit id
    private String reservationId;                // Reference to the Reservation that this rental is associated with

    @Override
    public String genId() {
        return RentalRecord.genId(reservationId);
    }
}
