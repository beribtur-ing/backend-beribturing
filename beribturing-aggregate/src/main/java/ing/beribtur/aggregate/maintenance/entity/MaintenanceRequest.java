package ing.beribtur.aggregate.maintenance.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.item.entity.ProductVariant;
import ing.beribtur.aggregate.maintenance.entity.sdo.MaintenanceRequestCdo;
import ing.beribtur.aggregate.maintenance.entity.vo.MaintenanceStatus;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.entity.Lender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a maintenance request for a rented item.
 * This is an important accent entity for a renting application as it allows
 * lendees to report issues with rented items and request maintenance.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceRequest extends DomainEntity {
    //
    private String productVariantId;      // Reference to Product Variant
    private String requesterId;           // Reference to the Lendee who made the request
    private String ownerId;               // Reference to the Lender who owns the product
    private String rentalRecordId;        // Reference to the RentalRecord associated with this request
    private String description;           // Description of the issue
    private MaintenanceStatus status;     // Status of the maintenance request
    private LocalDateTime requestedAt;    // When the maintenance was requested
    private LocalDateTime resolvedAt;     // When the maintenance was resolved
    private String resolution;            // Description of how the issue was resolved

    // accent relationships
    private transient ProductVariant productVariant;    // The product variant needing maintenance
    private transient Lendee requester;                 // The Lendee who made the request
    private transient Lender owner;                     // The Lender who owns the product
    private transient RentalRecord rentalRecord;        // The rental record associated with this request
    private transient List<MaintenancePhoto> photos;    // Photos attached to this maintenance request

    public MaintenanceRequest(MaintenanceRequestCdo maintenanceRequestCdo) {
        //
        super(maintenanceRequestCdo.genId());
        BeanUtils.copyProperties(maintenanceRequestCdo, this);
    }

    public static String genId(String productVariantId, long sequence) {
        //
        return String.format("%s-%d", productVariantId, sequence);
    }

    @Override
    protected void modifyAttributes(NameValueList nameValues) {
        //
        for (NameValue nameValue : nameValues.list()) {
            String value = nameValue.getValue();
            switch (nameValue.getName().trim()) {
                case "productVariantId" -> this.productVariantId = value;
                case "requesterId" -> this.requesterId = value;
                case "ownerId" -> this.ownerId = value;
                case "rentalRecordId" -> this.rentalRecordId = value;
                case "description" -> this.description = value;
                case "status" -> this.status = MaintenanceStatus.valueOf(value);
                case "requestedAt" -> this.requestedAt = LocalDateTime.parse(value);
                case "resolvedAt" -> this.resolvedAt = LocalDateTime.parse(value);
                case "resolution" -> this.resolution = value;
                default -> throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }
}
