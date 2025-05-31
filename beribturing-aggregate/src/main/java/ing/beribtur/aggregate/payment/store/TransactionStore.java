package ing.beribtur.aggregate.payment.store;


import ing.beribtur.aggregate.payment.entity.Transaction;

import java.util.List;

public interface TransactionStore {
    //
    Transaction create(Transaction transaction);
    Transaction retrieve(String id);
    List<Transaction> retrieveAll(List<String> ids);
    Transaction update(Transaction transaction);
    void delete(String id);
}
