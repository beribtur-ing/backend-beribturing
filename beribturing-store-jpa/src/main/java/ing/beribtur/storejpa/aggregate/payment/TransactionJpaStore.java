package ing.beribtur.storejpa.aggregate.payment;

import ing.beribtur.aggregate.payment.entity.Transaction;
import ing.beribtur.aggregate.payment.entity.vo.PaymentStatus;
import ing.beribtur.aggregate.payment.store.TransactionStore;
import ing.beribtur.storejpa.aggregate.payment.jpo.TransactionJpo;
import ing.beribtur.storejpa.aggregate.payment.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TransactionJpaStore implements TransactionStore {
    //
    private final TransactionRepository transactionRepository;

    @Override
    public void create(Transaction transaction) {
        TransactionJpo transactionJpo = new TransactionJpo(transaction);

        transactionRepository.save(transactionJpo);
        transaction.setId(transactionJpo.getId());
    }

    @Override
    public Transaction retrieve(String id) {
        TransactionJpo transactionJpo = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found: " + id));
        return transactionJpo.toDomain();
    }

    @Override
    public List<Transaction> retrieveAll(List<String> ids) {
        List<TransactionJpo> transactionJpos = transactionRepository.findAllById(ids);
        return transactionJpos.stream()
                .map(TransactionJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Transaction transaction) {
        TransactionJpo transactionJpo = transactionRepository.findById(transaction.getId())
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found: " + transaction.getId()));
        
        // Update the JPO with the domain entity's values
        TransactionJpo updatedJpo = new TransactionJpo(transaction);
        updatedJpo.setEntityVersion(transactionJpo.getEntityVersion());
        updatedJpo.setRegisteredBy(transactionJpo.getRegisteredBy());
        updatedJpo.setRegisteredOn(transactionJpo.getRegisteredOn());
        
        transactionRepository.save(updatedJpo);
    }

    @Override
    public void delete(String id) {
        transactionRepository.deleteById(id);
    }
    
    // Additional methods for specific queries
    public List<Transaction> findByRentalRecordId(String rentalRecordId) {
        return TransactionJpo.toDomains(transactionRepository.findByRentalRecordId(rentalRecordId));
    }
    
    public List<Transaction> findByPayerId(String payerId) {
        return TransactionJpo.toDomains(transactionRepository.findByPayerId(payerId));
    }
    
    public List<Transaction> findByPayeeId(String payeeId) {
        return TransactionJpo.toDomains(transactionRepository.findByPayeeId(payeeId));
    }
    
    public List<Transaction> findByStatus(PaymentStatus status) {
        return TransactionJpo.toDomains(transactionRepository.findByStatus(status.name()));
    }
    
    public List<Transaction> findByPaymentProvider(String paymentProvider) {
        return TransactionJpo.toDomains(transactionRepository.findByPaymentProvider(paymentProvider));
    }
    
    public List<Transaction> findByRentalRecordIdAndStatus(String rentalRecordId, PaymentStatus status) {
        return TransactionJpo.toDomains(
            transactionRepository.findByRentalRecordIdAndStatus(rentalRecordId, status.name())
        );
    }
}