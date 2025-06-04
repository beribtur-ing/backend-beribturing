package ing.beribtur.aggregate.account.logic;

import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.store.AccountStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountLogic {
    //
    private final AccountStore accountStore;

    public Account findByPhoneNumber(String phoneNumber) {
        return accountStore.findByPhoneNumber(phoneNumber);
    }

    public boolean existsPhoneAndRole(String phoneNumber, String role) {
        return accountStore.existsByPhoneNumberAndRole(phoneNumber, role);
    }

    public void create(Account account) {
        accountStore.create(account);
    }

    public void update(Account account) {
        accountStore.update(account);
    }

    public Account findByPhoneNumberAndRole(String phoneNumber, String role) {
        return accountStore.findByPhoneNumberAndRole(phoneNumber, role);
    }
}
