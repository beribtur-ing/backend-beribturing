package ing.beribtur.storejpa.aggregate.user.repository;

import ing.beribtur.storejpa.aggregate.user.jpo.LenderJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LenderRepository extends JpaRepository<LenderJpo, String> {
    List<LenderJpo> findByPhoneNumber(String phoneNumber);
    List<LenderJpo> findByEmail(String email);
    List<LenderJpo> findByIsActive(boolean isActive);
    List<LenderJpo> findByLanderType(String landerType);
}

