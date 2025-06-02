package ing.beribtur.storejpa.aggregate.user.repository;

import ing.beribtur.storejpa.aggregate.user.jpo.LendeeJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LendeeRepository extends JpaRepository<LendeeJpo, String> {
    List<LendeeJpo> findByPhoneNumber(String phoneNumber);
    List<LendeeJpo> findByEmail(String email);
    List<LendeeJpo> findByIsActive(boolean isActive);
}

