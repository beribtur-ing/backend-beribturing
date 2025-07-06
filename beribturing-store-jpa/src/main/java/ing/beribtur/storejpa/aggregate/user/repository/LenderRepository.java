package ing.beribtur.storejpa.aggregate.user.repository;

import ing.beribtur.storejpa.aggregate.user.jpo.LenderJpo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LenderRepository extends JpaRepository<LenderJpo, String> {
    LenderJpo findByPhoneNumber(String phoneNumber);
    List<LenderJpo> findByEmail(String email);
    Page<LenderJpo> findByActive(boolean isActive, Pageable pageable);
    List<LenderJpo> findByLenderType(String landerType);

    // Count methods for statistics
    long countByActive(boolean isActive);
}

