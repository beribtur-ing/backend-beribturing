package ing.beribtur.aggregate.shared.store;



import ing.beribtur.aggregate.shared.entity.Account;

import java.util.List;

public interface AccountStore {
    //
    Account create(Account account);
    Account retrieve(String id);
    List<Account> retrieveAll(List<String> ids);
    Account update(Account account);
    void delete(String id);
    Account findByPhoneNumber(String phoneNumber);
}
