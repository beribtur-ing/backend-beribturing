package ing.beribtur.aggregate.account.store;



import ing.beribtur.aggregate.account.entity.Account;

import java.util.List;

public interface AccountStore {
    //
    void create(Account account);
    Account retrieve(String id);
    List<Account> retrieveAll(List<String> ids);
    void update(Account account);
    void delete(String id);
    Account findByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);
}
