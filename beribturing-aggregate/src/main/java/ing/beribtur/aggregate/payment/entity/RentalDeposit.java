package ing.beribtur.aggregate.payment.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.aggregate.payment.entity.vo.Currency;
import ing.beribtur.aggregate.payment.entity.vo.DepositStatus;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.user.entity.Landee;

import java.time.LocalDateTime;

public class RentalDeposit extends DomainEntity {
    //
    private String rentalRecordId;
    private String payerId;                 // Lendee who paid
    private Currency amount;
    private DepositStatus status;
    private LocalDateTime paidAt;
    private LocalDateTime resolvedAt;
    private String notes;                // Optional: reason for withholding or comments

    // Domain relationships
    private transient RentalRecord rentalRecord; // The rental record this deposit is associated with
    private transient Landee payer;              // The Landee who paid the deposit
}
