package ing.beribtur.aggregate.rental.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.aggregate.rental.entity.vo.ConditionCheckType;
import ing.beribtur.aggregate.user.entity.vo.ConditionCheckable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemConditionCheck extends DomainEntity {
    //
    private String rentalRecordId; // The ID of the RentalRecord this check belongs to
    private String variantId; // The ID of the ItemVariant being checked
    private String checkedBy; // The ID of the Lendee or Lender who performed the check
    private ConditionCheckType checkType;

    // Domain relationships
    private transient RentalRecord rentalRecord;
    private transient ConditionCheckable checker;
    private transient List<ItemConditionPhoto> photos;
}

