package ing.beribtur.storejpa.aggregate.account.repository;

import ing.beribtur.storejpa.aggregate.account.jpo.AccountJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountJpo, String> {
    Optional<AccountJpo> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndRole(String phoneNumber, String role);

    Optional<AccountJpo> findByPhoneNumberAndRole(String phoneNumber, String role);
}
