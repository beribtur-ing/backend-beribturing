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
}
