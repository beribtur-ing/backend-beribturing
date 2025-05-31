package ing.beribtur.aggregate.rental.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.rental.entity.vo.ConditionCheckType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemConditionCheckCdo extends CreationDataObject {
    //
    private String rentalRecordId; // The ID of the RentalRecord this check belongs to
    private String variantId; // The ID of the ItemVariant being checked
    private String checkedBy; // The ID of the Lendee or Lender who performed the check
    private ConditionCheckType checkType;
}
