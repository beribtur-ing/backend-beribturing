package ing.beribtur.aggregate.maintenance.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.maintenance.entity.MaintenanceRequest;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceRequestCdo extends CreationDataObject {
    //
    private String productVariantId;     // Reference to Product Variant
    private String requesterId;          // Reference to the Lendee who made the request
    private String ownerId;              // Reference to the Lender who owns the product
    private String rentalRecordId;       // Reference to the RentalRecord associated with this request
    private String description;          // Description of the issue
    private long sequence;

    public String genId() {
        //
        return MaintenanceRequest.genId(productVariantId, sequence);
    }
}

