package ing.beribtur.aggregate.rental.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.rental.entity.sdo.ItemConditionCheckCdo;
import ing.beribtur.aggregate.rental.entity.vo.ConditionCheckType;
import ing.beribtur.aggregate.user.entity.vo.ConditionCheckable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

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
    private long photoSequence; // Sequence number for photos, starting from 1

    // Domain relationships
    private transient RentalRecord rentalRecord;
    private transient ConditionCheckable checker;
    private transient List<ItemConditionPhoto> photos;

    public ItemConditionCheck(ItemConditionCheckCdo itemConditionCheckCdo) {
        //
        super(itemConditionCheckCdo.genId());
        BeanUtils.copyProperties(itemConditionCheckCdo, this);
        this.photoSequence = 1L; // Initialize photo sequence to 1
    }

    public static String genId(String rentalRecordId, String checkType) {
        //
        return String.format("%s-%s", rentalRecordId, checkType);
    }

    @Override
    protected void modifyAttributes(NameValueList nameValues) {
        for (NameValue nameValue : nameValues.list()) {
            String value = nameValue.getValue();
            switch (nameValue.getName().trim()) {
                case "checkType" -> this.checkType = ConditionCheckType.valueOf(value);
                default -> throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }
}

