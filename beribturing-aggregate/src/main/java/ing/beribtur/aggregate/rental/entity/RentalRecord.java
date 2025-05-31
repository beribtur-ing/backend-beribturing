package ing.beribtur.aggregate.rental.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.aggregate.item.entity.ProductVariant;
import ing.beribtur.aggregate.payment.entity.Discount;
import ing.beribtur.aggregate.payment.entity.RentalDeposit;
import ing.beribtur.aggregate.rental.entity.vo.RentalStatus;
import ing.beribtur.aggregate.shared.entity.Period;
import ing.beribtur.aggregate.user.entity.Landee;

import java.time.LocalDateTime;
import java.util.UUID;

public class RentalRecord extends DomainEntity {
    //
    private Period period;
    private LocalDateTime rentedAt;       // The date and time when the product was rented
    private LocalDateTime returnedAt;     // The date when the product was returned
    private LocalDateTime cancelledAt;    // The date when the rental was canceled
    private UUID productVariantId;              // Reference to Product Variant
    private RentalStatus status;
    private UUID landeeId;                      // Reference to the Reservation that this rental is associated with
    private Double fee;
    private UUID discountId;                    // Reference to any discount applied to the rental
    private UUID depositId;                     // Rental Deposit id

    // Domain relationships
    private transient ProductVariant productVariant;    // The product variant being rented
    private transient Reservation reservation;          // The reservation that this rental is associated with
    private transient Landee landee;                    // The Landee who made the rental
    private transient Discount discount;
    private transient RentalDeposit deposit;            // The deposit associated with this rental

    private transient ItemConditionCheck beforeRented;
    private transient ItemConditionCheck afterReturned;


    private static UUID genId(UUID reservationId) {
        //
        return reservationId == null ? UUID.randomUUID() : UUID.nameUUIDFromBytes(reservationId.toString().getBytes());
    }
}
