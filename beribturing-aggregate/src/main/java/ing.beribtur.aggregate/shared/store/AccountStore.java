package ing.beribtur.aggregate.shared.store;



import ing.beribtur.aggregate.shared.entity.Account;

import java.util.List;

public interface AccountStore {
    //
    Account create(Account account);
    Account retrieve(Long id);
    List<Account> retrieveAll(List<Long> ids);
    Account update(Account account);
    void delete(Long id);
}
