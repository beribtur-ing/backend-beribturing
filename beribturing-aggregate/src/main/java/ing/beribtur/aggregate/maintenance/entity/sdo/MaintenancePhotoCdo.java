package ing.beribtur.aggregate.maintenance.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.maintenance.entity.MaintenancePhoto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintenancePhotoCdo extends CreationDataObject {
    //
    private String maintenanceRequestId;
    private String url;
    private String description;
    private int order;

    public String genId() {
        //
        return MaintenancePhoto.genId(maintenanceRequestId, order);

    }
}

