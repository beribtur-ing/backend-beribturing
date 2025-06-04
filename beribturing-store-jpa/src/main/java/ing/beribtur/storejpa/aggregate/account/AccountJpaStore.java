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
        AccountJpo existingJpo = accountRepository.findById(account.getId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + account.getId()));

        AccountJpo updatedJpo = new AccountJpo(account);
        updatedJpo.setEntityVersion(existingJpo.getEntityVersion());
        updatedJpo.setRegisteredBy(existingJpo.getRegisteredBy());
        updatedJpo.setRegisteredOn(existingJpo.getRegisteredOn());

        accountRepository.save(updatedJpo);
    }

    @Override
    public void delete(String id) {
        if (!accountRepository.existsById(id)) {
            throw new IllegalArgumentException("Account not found: " + id);
        }
        accountRepository.deleteById(id);
    }

    @Override
    public Account findByPhoneNumber(String phoneNumber) {
        AccountJpo accountJpo = accountRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found with phone number: " + phoneNumber));
        return accountJpo.toDomain();
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return accountRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public Account findByPhoneNumberAndRole(String phoneNumber, String role) {
        return accountRepository.findByPhoneNumberAndRole(phoneNumber, role)
                .map(AccountJpo::toDomain)
                .orElseThrow(() -> new IllegalArgumentException("Account not found with phone number: " + phoneNumber));
    }
}
