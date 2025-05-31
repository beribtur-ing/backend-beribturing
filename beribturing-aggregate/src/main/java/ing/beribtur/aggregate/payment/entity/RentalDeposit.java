package ing.beribtur.aggregate.payment.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.aggregate.payment.entity.vo.DepositStatus;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.user.entity.Lendee;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.UUID;

public class RentalDeposit extends DomainEntity {
    //
    private UUID rentalRecordId;
    private UUID payerId;                 // Lendee who paid
    private Currency amount;
    private DepositStatus status;
    private LocalDateTime paidAt;
    private LocalDateTime resolvedAt;
    private String notes;                // Optional: reason for withholding or comments

    // Domain relationships
    private transient RentalRecord rentalRecord; // The rental record this deposit is associated with
    private transient Lendee payer;              // The Lendee who paid the deposit
}
