package ing.beribtur.storejpa.aggregate.maintenance.repository;

import ing.beribtur.storejpa.aggregate.maintenance.jpo.MaintenancePhotoJpo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenancePhotoRepository extends JpaRepository<MaintenancePhotoJpo,String> {
}
