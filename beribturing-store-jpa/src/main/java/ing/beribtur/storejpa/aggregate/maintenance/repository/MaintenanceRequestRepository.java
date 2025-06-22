package ing.beribtur.storejpa.aggregate.maintenance.repository;

import ing.beribtur.storejpa.aggregate.maintenance.jpo.MaintenanceRequestJpo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequestJpo,String> {
}
