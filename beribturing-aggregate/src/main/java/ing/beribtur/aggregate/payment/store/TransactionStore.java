package ing.beribtur.aggregate.payment.store;


import ing.beribtur.aggregate.payment.entity.Transaction;

import java.util.List;

public interface TransactionStore {
    //
    void create(Transaction transaction);
    Transaction retrieve(String id);
    List<Transaction> retrieveAll(List<String> ids);
    void update(Transaction transaction);
    void delete(String id);
}
