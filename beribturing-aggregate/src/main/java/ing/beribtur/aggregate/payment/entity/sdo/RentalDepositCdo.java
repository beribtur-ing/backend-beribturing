package ing.beribtur.aggregate.payment.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.payment.entity.vo.Currency;
import ing.beribtur.aggregate.payment.entity.vo.DepositStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalDepositCdo extends CreationDataObject {
    //
    private String rentalRecordId;
    private String payerId;                 // Lendee who paid
    private Currency amount;
    private DepositStatus status;
    private LocalDateTime paidAt;
    private LocalDateTime resolvedAt;
    private String notes;                // Optional: reason for withholding or comments
}
