package ing.beribtur.storejpa.aggregate.rental.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.payment.entity.vo.Currency;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.rental.entity.vo.Period;
import ing.beribtur.aggregate.rental.entity.vo.RentalStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "RENTAL_RECORD")
public class RentalRecordJpo extends DomainEntityJpo {

    // Period
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private LocalDateTime rentedAt;
    private LocalDateTime returnedAt;
    private LocalDateTime cancelledAt;

    @Column(nullable = false)
    private String productVariantId;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String lendeeId;

    @Column(nullable = false)
    private String ownerId;

    // Fee
    private BigDecimal feeAmount;
    private String feeCurrency;

    private String discountId;
    private String depositId;

    public RentalRecordJpo(RentalRecord rentalRecord) {
        //
        super(rentalRecord);
        BeanUtils.copyProperties(rentalRecord, this);

        // Period
        if (rentalRecord.getPeriod() != null) {
            this.startDateTime = rentalRecord.getPeriod().getStartDateTime();
            this.endDateTime = rentalRecord.getPeriod().getEndDateTime();
        }

        // Fee
        if (rentalRecord.getFee() != null) {
            this.feeAmount = rentalRecord.getFee().getAmount();
            this.feeCurrency = rentalRecord.getFee().getCurrency();
        }

        // Enum to String
        if (rentalRecord.getStatus() != null) {
            this.status = rentalRecord.getStatus().name();
        }
    }

    public RentalRecord toDomain() {
        //
        RentalRecord rentalRecord = new RentalRecord();
        BeanUtils.copyProperties(this, rentalRecord);

        // Period
        if (this.startDateTime != null && this.endDateTime != null) {
            rentalRecord.setPeriod(new Period(this.startDateTime, this.endDateTime));
        }

        // Fee
        if (this.feeAmount != null && this.feeCurrency != null) {
            rentalRecord.setFee(new Currency(this.feeAmount, this.feeCurrency));
        }

        // Enum
        if (this.status != null) {
            rentalRecord.setStatus(RentalStatus.valueOf(this.status));
        }

        return rentalRecord;
    }

    public static List<RentalRecord> toDomains(List<RentalRecordJpo> jpos) {
        //
        return jpos.stream().map(RentalRecordJpo::toDomain).toList();
    }
}
