package ing.beribtur.storejpa.aggregate.beribturing.repository;

import ing.beribtur.storejpa.aggregate.beribturing.jpo.AccountJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountJpo, String> {
    Optional<AccountJpo> findByPhoneNumber(String phoneNumber);
}
