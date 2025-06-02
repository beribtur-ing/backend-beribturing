package ing.beribtur.storejpa.aggregate.payment.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.payment.entity.RentalDeposit;
import ing.beribtur.aggregate.payment.entity.vo.Currency;
import ing.beribtur.aggregate.payment.entity.vo.DepositStatus;
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
@Table(name = "RENTAL_DEPOSIT")
public class RentalDepositJpo extends DomainEntityJpo {

    @Column(nullable = false)
    private String rentalRecordId;
    
    @Column(nullable = false)
    private String payerId;
    
    // Currency fields
    @Column(nullable = false)
    private BigDecimal amount;
    
    @Column(nullable = false)
    private String currency;
    
    @Column(nullable = false)
    private String status;
    
    private LocalDateTime paidAt;
    
    private LocalDateTime resolvedAt;
    
    private String notes;

    public RentalDepositJpo(RentalDeposit rentalDeposit) {
        //
        super(rentalDeposit);
        BeanUtils.copyProperties(rentalDeposit, this);
        
        // Handle Currency
        if (rentalDeposit.getAmount() != null) {
            this.amount = rentalDeposit.getAmount().getAmount();
            this.currency = rentalDeposit.getAmount().getCurrency();
        }
        
        // Handle DepositStatus
        if (rentalDeposit.getStatus() != null) {
            this.status = rentalDeposit.getStatus().name();
        }
    }

    public RentalDeposit toDomain() {
        //
        RentalDeposit rentalDeposit = new RentalDeposit();
        BeanUtils.copyProperties(this, rentalDeposit);
        
        // Reconstruct Currency
        if (this.amount != null && this.currency != null) {
            rentalDeposit.setAmount(new Currency(this.amount, this.currency));
        }
        
        // Convert string back to enum
        if (this.status != null) {
            rentalDeposit.setStatus(DepositStatus.valueOf(this.status));
        }
        
        return rentalDeposit;
    }

    public static List<RentalDeposit> toDomains(List<RentalDepositJpo> jpos) {
        //
        return jpos.stream().map(RentalDepositJpo::toDomain).toList();
    }
}