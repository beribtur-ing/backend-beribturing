package ing.beribtur.storejpa.aggregate.account.repository;

import ing.beribtur.storejpa.aggregate.account.jpo.SystemSettingsJpo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemSettingsRepository extends JpaRepository<SystemSettingsJpo, String> {
}
