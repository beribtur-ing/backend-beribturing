package ing.beribtur.storejpa.aggregate.notification.repository;

import ing.beribtur.storejpa.aggregate.notification.jpo.ContactJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<ContactJpo, String> {
    Optional<ContactJpo> findByUserId(String userId);
    void deleteByUserId(String userId);
}