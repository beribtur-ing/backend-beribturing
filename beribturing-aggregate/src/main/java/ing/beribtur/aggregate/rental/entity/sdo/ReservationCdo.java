package ing.beribtur.aggregate.rental.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.rental.entity.Reservation;
import ing.beribtur.aggregate.rental.entity.vo.Period;
import ing.beribtur.aggregate.rental.entity.vo.ReservationStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationCdo extends CreationDataObject {
    //
    private String productVariantId;      // Reference to Product Variant
    private String requesterId;           // Reference to the Lendee who made the reservation
    private String ownerId;          // Reference to the Lender who owns the product
    private Period period;              // The period for which the product is reserved
    private ReservationStatus status;
    private String note;
    private long reservationSequence; // Unique sequence for the reservation

    @Override
    public String genId() {
        return Reservation.genId(productVariantId, reservationSequence);
    }
}
