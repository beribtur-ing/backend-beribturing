package ing.beribtur.aggregate.maintenance;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.item.entity.ProductVariant;
import ing.beribtur.aggregate.maintenance.vo.MaintenanceStatus;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.entity.Lender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Override
    protected void modifyAttributes(NameValueList var1) {

    }
}
