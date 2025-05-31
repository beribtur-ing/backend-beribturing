package ing.beribtur.aggregate.payment.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.aggregate.payment.entity.vo.Currency;
import ing.beribtur.aggregate.payment.entity.vo.DepositStatus;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.user.entity.Lendee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
    private transient Lendee payer;              // The Lendee who paid the deposit
}
