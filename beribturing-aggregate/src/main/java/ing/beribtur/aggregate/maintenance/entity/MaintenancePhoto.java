package ing.beribtur.aggregate.maintenance.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.maintenance.entity.sdo.MaintenancePhotoCdo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

/**
 * Represents a photo attached to a maintenance request.
 * This allows lendees to provide visual evidence of issues with rented items.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MaintenancePhoto extends DomainEntity {
    //
    private String maintenanceRequestId;  // Reference to the MaintenanceRequest this photo is attached to
    private String url;                   // URL to the photo
    private String description;           // Optional description of what the photo shows
    private int photoOrder;               // Order of the photo in the maintenance request

    // Domain relationships
    private transient MaintenanceRequest maintenanceRequest;  // The maintenance request this photo is attached to


    public MaintenancePhoto(MaintenancePhotoCdo maintenancePhotoCdo) {
        //
        super(maintenancePhotoCdo.genId());
        BeanUtils.copyProperties(maintenancePhotoCdo, this);
    }

    public static String genId(String maintenanceRequestId, int order) {
        //
        return String.format("%s-%d", maintenanceRequestId, order);
    }

    @Override
    protected void modifyAttributes(NameValueList nameValues) {
        //
        for (NameValue nameValue : nameValues.list()) {
            String value = nameValue.getValue();
            switch (nameValue.getName().trim()) {
                case "maintenanceRequestId" -> this.maintenanceRequestId = value;
                case "url" -> this.url = value;
                case "description" -> this.description = value;
                case "photoOrder" -> this.photoOrder = Integer.parseInt(value);
                default -> throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }
}
