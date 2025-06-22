package ing.beribtur.storejpa.aggregate.user.repository;

import ing.beribtur.storejpa.aggregate.user.jpo.LenderJpo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LenderRepository extends JpaRepository<LenderJpo, String> {
    LenderJpo findByPhoneNumber(String phoneNumber);
    List<LenderJpo> findByEmail(String email);
    Page<LenderJpo> findByActive(boolean isActive, Pageable pageable);
    List<LenderJpo> findByLenderType(String landerType);
}

