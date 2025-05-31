package ing.beribtur.aggregate.rental.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.aggregate.item.entity.ProductVariant;
import ing.beribtur.aggregate.rental.entity.vo.ReservationStatus;
import ing.beribtur.aggregate.shared.entity.Period;
import ing.beribtur.aggregate.user.entity.Landee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation extends DomainEntity {
    //
    private UUID productVariantId;      // Reference to Product Variant
    private UUID requesterId;           // Reference to the Landee who made the reservation
    private Period period;              // The period for which the product is reserved
    private ReservationStatus status;
    private String note;

    // Domain relationships
    private transient ProductVariant productVariant;    // The product variant being reserved
    private transient Landee requester;                 // The Landee who made the reservation
}
