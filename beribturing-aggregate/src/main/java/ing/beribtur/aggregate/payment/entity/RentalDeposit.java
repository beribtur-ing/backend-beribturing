package ing.beribtur.aggregate.payment.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.util.JsonUtil;
import ing.beribtur.aggregate.payment.entity.sdo.RentalDepositCdo;
import ing.beribtur.aggregate.payment.entity.vo.Currency;
import ing.beribtur.aggregate.payment.entity.vo.DepositStatus;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.user.entity.Lendee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

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

    public RentalDeposit(RentalDepositCdo rentalDepositCdo) {
        //
        super(rentalDepositCdo.genId());
        BeanUtils.copyProperties(rentalDepositCdo, this);
    }

    @Override
    protected void modifyAttributes(NameValueList nameValues) {
        for (NameValue nameValue : nameValues.list()) {
            String value = nameValue.getValue();
            switch (nameValue.getName().trim()) {
                case "rentalRecordId" -> this.rentalRecordId = value;
                case "payerId" -> this.payerId = value;
                case "amount" -> this.amount = JsonUtil.fromJson(value, Currency.class);
                case "status" -> this.status = DepositStatus.valueOf(value);
                case "paidAt" -> this.paidAt = LocalDateTime.parse(value);
                case "resolvedAt" -> this.resolvedAt = LocalDateTime.parse(value);
                case "notes" -> this.notes = value;
                default -> throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }
}
