package ing.beribtur.storejpa.aggregate.user.repository;

import ing.beribtur.storejpa.aggregate.user.jpo.LenderJpo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LenderRepository extends JpaRepository<LenderJpo, String> {
    LenderJpo findByPhoneNumber(String phoneNumber);
}

