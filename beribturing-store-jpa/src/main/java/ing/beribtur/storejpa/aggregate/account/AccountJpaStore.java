package ing.beribtur.storejpa.aggregate.account;

import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.store.AccountStore;
import ing.beribtur.storejpa.aggregate.account.jpo.AccountJpo;
import ing.beribtur.storejpa.aggregate.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class AccountJpaStore implements AccountStore {
    //
    private final AccountRepository accountRepository;

    @Override
    public void create(Account account) {
        AccountJpo accountJpo = new AccountJpo(account);

        accountRepository.save(accountJpo);
        account.setId(accountJpo.getId());
    }

    @Override
    public Account retrieve(String id) {
        AccountJpo accountJpo = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + id));
        return accountJpo.toDomain();
    }

    @Override
    public List<Account> retrieveAll(List<String> ids) {
        List<AccountJpo> accountJpos = accountRepository.findAllById(ids);
        return accountJpos.stream()
                .map(AccountJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Account account) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Account findByPhoneNumber(String phoneNumber) {
        AccountJpo accountJpo = accountRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found with phone number: " + phoneNumber));
        return accountJpo.toDomain();
    }
}
