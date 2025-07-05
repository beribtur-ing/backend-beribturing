package ing.beribtur.aggregate.payment.logic;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.util.Entities;
import ing.beribtur.aggregate.payment.entity.Transaction;
import ing.beribtur.aggregate.payment.entity.sdo.TransactionCdo;
import ing.beribtur.aggregate.payment.entity.vo.PaymentStatus;
import ing.beribtur.aggregate.payment.store.TransactionStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionLogic {
    //
    private final TransactionStore transactionStore;

    public String registerTransaction(TransactionCdo transactionCdo) {
        //
        Transaction transaction = new Transaction(transactionCdo);
        if (transactionStore.exists(transaction.getId())) {
            throw new IllegalArgumentException("Transaction already exists. " + transaction.getId());
        }
        transactionStore.create(transaction);
        return transaction.getId();
    }

    public List<String> registerTransactions(List<TransactionCdo> transactionCdos) {
        //
        return transactionCdos.stream().map(this::registerTransaction).collect(Collectors.toList());
    }

    public Transaction findTransaction(String transactionId) {
        //
        Transaction transaction = transactionStore.retrieve(transactionId);
        if (transaction == null) {
            throw new NoSuchElementException("Transaction id: " + transactionId);
        }
        return transaction;
    }

    public List<Transaction> findTransactions(List<String> transactionIds) {
        //
        return transactionStore.retrieveAll(transactionIds);
    }

    public List<Transaction> findTransactions(Offset offset) {
        //
        return transactionStore.retrieveList(offset);
    }

    public void modifyTransaction(String transactionId, NameValueList nameValues) {
        //
        Transaction transaction = findTransaction(transactionId);
        transaction.modify(nameValues);
        transactionStore.update(transaction);
    }

    public void modifyTransaction(Transaction transaction) {
        //
        Transaction oldTransaction = findTransaction(transaction.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldTransaction, transaction);
        if (!nameValues.list().isEmpty()) {
            modifyTransaction(transaction.getId(), nameValues);
        }
    }

    public void removeTransaction(String transactionId) {
        //
        Transaction transaction = findTransaction(transactionId);
        transactionStore.delete(transaction.getId());
    }

    public boolean existsTransaction(String transactionId) {
        //
        return transactionStore.exists(transactionId);
    }

    public List<Transaction> findByRentalRecordId(UUID rentalRecordId) {
        //
        return transactionStore.retrieveByRentalRecordId(rentalRecordId);
    }

    public List<Transaction> findByPayerId(UUID payerId) {
        //
        return transactionStore.retrieveByPayerId(payerId);
    }

    public List<Transaction> findByPayeeId(UUID payeeId) {
        //
        return transactionStore.retrieveByPayeeId(payeeId);
    }

    public List<Transaction> findByStatus(PaymentStatus status) {
        //
        return transactionStore.retrieveByStatus(status);
    }

    public void markAsCompleted(String transactionId) {
        //
        Transaction transaction = findTransaction(transactionId);
        transaction.setStatus(PaymentStatus.SUCCESSFUL);
        transaction.setCompletedAt(LocalDateTime.now());
        transactionStore.update(transaction);
    }

    public void markAsFailed(String transactionId) {
        //
        Transaction transaction = findTransaction(transactionId);
        transaction.setStatus(PaymentStatus.FAILED);
        transactionStore.update(transaction);
    }

    public void markAsRefunded(String transactionId) {
        //
        Transaction transaction = findTransaction(transactionId);
        transaction.setStatus(PaymentStatus.REFUNDED);
        transactionStore.update(transaction);
    }

    public List<Transaction> findPendingTransactions() {
        //
        return transactionStore.retrieveByStatus(PaymentStatus.PENDING);
    }

    public List<Transaction> findCompletedTransactions() {
        //
        return transactionStore.retrieveByStatus(PaymentStatus.SUCCESSFUL);
    }

    public double calculateMonthlyRevenueOfOwner(String ownerId, LocalDateTime startOfMonth, LocalDateTime endOfMonth) {
        //
        return transactionStore.calculateMonthlyRevenueByOwnerId(ownerId, startOfMonth, endOfMonth);
    }
}
