package ing.beribtur.aggregate.payment.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.util.JsonUtil;
import ing.beribtur.aggregate.payment.entity.sdo.TransactionCdo;
import ing.beribtur.aggregate.payment.entity.vo.Currency;
import ing.beribtur.aggregate.payment.entity.vo.PaymentStatus;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.entity.Lender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

    public Transaction(TransactionCdo transactionCdo) {
        //
        super(transactionCdo.genId());
        BeanUtils.copyProperties(transactionCdo, this);
    }

    @Override
    protected void modifyAttributes(NameValueList nameValues) {
        for (NameValue nameValue : nameValues.list()) {
            String value = nameValue.getValue();
            switch (nameValue.getName().trim()) {
                case "rentalRecordId" -> this.rentalRecordId = UUID.fromString(value);
                case "payerId" -> this.payerId = UUID.fromString(value);
                case "payeeId" -> this.payeeId = UUID.fromString(value);
                case "totalAmount" -> this.totalAmount = JsonUtil.fromJson(value, Currency.class);
                case "commissionAmount" -> this.commissionAmount = JsonUtil.fromJson(value, Currency.class);
                case "payeeAmount" -> this.payeeAmount = JsonUtil.fromJson(value, Currency.class);
                case "status" -> this.status = PaymentStatus.valueOf(value);
                case "initiatedAt" -> this.initiatedAt = LocalDateTime.parse(value);
                case "completedAt" -> this.completedAt = LocalDateTime.parse(value);
                case "paymentProvider" -> this.paymentProvider = value;
                default -> throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }
}
