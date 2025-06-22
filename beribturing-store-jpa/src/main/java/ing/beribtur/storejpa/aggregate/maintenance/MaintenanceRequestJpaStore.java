package ing.beribtur.storejpa.aggregate.maintenance;

import ing.beribtur.aggregate.maintenance.store.MaintenanceRequestStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MaintenanceRequestJpaStore implements MaintenanceRequestStore {
}
