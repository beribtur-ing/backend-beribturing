package ing.beribtur.storejpa.aggregate.payment.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.payment.entity.Transaction;
import ing.beribtur.aggregate.payment.entity.vo.Currency;
import ing.beribtur.aggregate.payment.entity.vo.PaymentStatus;
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
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TRANSACTION")
public class TransactionJpo extends DomainEntityJpo {

    @Column(nullable = false)
    private String rentalRecordId;
    
    @Column(nullable = false)
    private String payerId;
    
    @Column(nullable = false)
    private String payeeId;
    
    // Total amount fields
    @Column(nullable = false)
    private BigDecimal totalAmount;
    
    @Column(nullable = false)
    private String totalCurrency;
    
    // Commission amount fields
    private BigDecimal commissionAmount;
    private String commissionCurrency;
    
    // Payee amount fields
    private BigDecimal payeeAmount;
    private String payeeCurrency;
    
    @Column(nullable = false)
    private String status;
    
    private LocalDateTime initiatedAt;
    
    private LocalDateTime completedAt;
    
    private String paymentProvider;

    public TransactionJpo(Transaction transaction) {
        //
        super(transaction);
        BeanUtils.copyProperties(transaction, this);
        
        // Convert UUID to String if needed
        if (transaction.getRentalRecordId() != null) {
            this.rentalRecordId = transaction.getRentalRecordId().toString();
        }
        if (transaction.getPayerId() != null) {
            this.payerId = transaction.getPayerId().toString();
        }
        if (transaction.getPayeeId() != null) {
            this.payeeId = transaction.getPayeeId().toString();
        }
        
        // Handle Currency fields
        if (transaction.getTotalAmount() != null) {
            this.totalAmount = transaction.getTotalAmount().getAmount();
            this.totalCurrency = transaction.getTotalAmount().getCurrency();
        }
        
        if (transaction.getCommissionAmount() != null) {
            this.commissionAmount = transaction.getCommissionAmount().getAmount();
            this.commissionCurrency = transaction.getCommissionAmount().getCurrency();
        }
        
        if (transaction.getPayeeAmount() != null) {
            this.payeeAmount = transaction.getPayeeAmount().getAmount();
            this.payeeCurrency = transaction.getPayeeAmount().getCurrency();
        }
        
        // Handle PaymentStatus
        if (transaction.getStatus() != null) {
            this.status = transaction.getStatus().name();
        }
    }

    public Transaction toDomain() {
        //
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(this, transaction);
        
        // Convert String to UUID if needed
        if (this.rentalRecordId != null) {
            transaction.setRentalRecordId(UUID.fromString(this.rentalRecordId));
        }
        if (this.payerId != null) {
            transaction.setPayerId(UUID.fromString(this.payerId));
        }
        if (this.payeeId != null) {
            transaction.setPayeeId(UUID.fromString(this.payeeId));
        }
        
        // Reconstruct Currency objects
        if (this.totalAmount != null && this.totalCurrency != null) {
            transaction.setTotalAmount(new Currency(this.totalAmount, this.totalCurrency));
        }
        
        if (this.commissionAmount != null && this.commissionCurrency != null) {
            transaction.setCommissionAmount(new Currency(this.commissionAmount, this.commissionCurrency));
        }
        
        if (this.payeeAmount != null && this.payeeCurrency != null) {
            transaction.setPayeeAmount(new Currency(this.payeeAmount, this.payeeCurrency));
        }
        
        // Convert string back to enum
        if (this.status != null) {
            transaction.setStatus(PaymentStatus.valueOf(this.status));
        }
        
        return transaction;
    }

    public static List<Transaction> toDomains(List<TransactionJpo> jpos) {
        //
        return jpos.stream().map(TransactionJpo::toDomain).toList();
    }
}