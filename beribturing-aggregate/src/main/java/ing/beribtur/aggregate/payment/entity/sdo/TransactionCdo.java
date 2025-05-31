package ing.beribtur.aggregate.payment.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.payment.entity.vo.Currency;
import ing.beribtur.aggregate.payment.entity.vo.PaymentStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionCdo extends CreationDataObject {
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
}
