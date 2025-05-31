package ing.beribtur.aggregate.rental.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.aggregate.rental.entity.vo.ConditionCheckType;
import ing.beribtur.aggregate.user.entity.vo.ConditionCheckable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemConditionCheck extends DomainEntity {
    //
    private UUID rentalRecordId; // The ID of the RentalRecord this check belongs to
    private UUID variantId; // The ID of the ItemVariant being checked
    private UUID checkedBy; // The ID of the Landee or Lander who performed the check
    private ConditionCheckType checkType;

    // Domain relationships
    private transient RentalRecord rentalRecord;
    private transient ConditionCheckable checker;
    private transient List<ItemConditionPhoto> photos;
}

