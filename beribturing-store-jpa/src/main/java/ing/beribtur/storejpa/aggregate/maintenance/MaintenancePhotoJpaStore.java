package ing.beribtur.storejpa.aggregate.maintenance;

import ing.beribtur.aggregate.maintenance.store.MaintenancePhotoStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MaintenancePhotoJpaStore implements MaintenancePhotoStore {
}
