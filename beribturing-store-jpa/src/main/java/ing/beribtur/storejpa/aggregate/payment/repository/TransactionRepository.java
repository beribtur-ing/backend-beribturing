package ing.beribtur.storejpa.aggregate.payment.repository;

import ing.beribtur.storejpa.aggregate.payment.jpo.TransactionJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionJpo, String> {
    List<TransactionJpo> findByRentalRecordId(String rentalRecordId);
    List<TransactionJpo> findByPayerId(String payerId);
    List<TransactionJpo> findByPayeeId(String payeeId);
    List<TransactionJpo> findByStatus(String status);
    List<TransactionJpo> findByPaymentProvider(String paymentProvider);
    List<TransactionJpo> findByRentalRecordIdAndStatus(String rentalRecordId, String status);
}