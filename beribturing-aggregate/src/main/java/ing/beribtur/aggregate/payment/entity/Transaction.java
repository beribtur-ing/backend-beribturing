package ing.beribtur.aggregate.payment.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.aggregate.payment.entity.vo.Currency;
import ing.beribtur.aggregate.payment.entity.vo.PaymentStatus;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.entity.Lender;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction extends DomainEntity {
    //
    private UUID rentalRecordId;              // The ID of the RentalRecord this transaction is associated with
    private UUID payerId;                       // Lendee
    private UUID payeeId;                       // Platform or Lender
    private Currency totalAmount;
    private Currency commissionAmount;          // Platform cut
    private Currency payeeAmount;               // Goes to lender
    private PaymentStatus status;
    private LocalDateTime initiatedAt;
    private LocalDateTime completedAt;
    private String paymentProvider;              // External gateway ref

    // Domain relationships
    private transient RentalRecord rentalRecord;
    private transient Lendee payer;
    private transient Lender payee;
}
