package ing.beribtur.aggregate.payment.store;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.payment.entity.Transaction;
import ing.beribtur.aggregate.payment.entity.vo.PaymentStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionStore {
    //
    void create(Transaction transaction);
    Transaction retrieve(String id);
    List<Transaction> retrieveAll(List<String> ids);
    List<Transaction> retrieveList(Offset offset);
    void update(Transaction transaction);
    void delete(String id);
    boolean exists(String id);

    List<Transaction> retrieveByRentalRecordId(UUID rentalRecordId);
    List<Transaction> retrieveByPayerId(UUID payerId);
    List<Transaction> retrieveByPayeeId(UUID payeeId);
    List<Transaction> retrieveByStatus(PaymentStatus status);

    double calculateMonthlyRevenueByOwnerId(String ownerId, LocalDateTime startOfMonth, LocalDateTime endOfMonth);
}
