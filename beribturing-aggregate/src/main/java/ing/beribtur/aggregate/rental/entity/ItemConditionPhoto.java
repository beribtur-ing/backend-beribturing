package ing.beribtur.aggregate.rental.entity;


import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.rental.entity.sdo.ItemConditionPhotoCdo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemConditionPhoto extends DomainEntity {
    //
    private String checkId; // The ID of the ItemConditionCheck this photo belongs to
    private String url;

    // Domain relationships
    private transient ItemConditionCheck itemConditionCheck;

    public ItemConditionPhoto(ItemConditionPhotoCdo itemConditionPhotoCdo) {
        //
        super(itemConditionPhotoCdo.genId());
        BeanUtils.copyProperties(itemConditionPhotoCdo, this);
    }

    public static String genId(String checkId, long sequence) {
        //
        return String.format("%s-%d", checkId, sequence);
    }

    @Override
    protected void modifyAttributes(NameValueList nameValues) {
        for (NameValue nameValue : nameValues.list()) {
            String value = nameValue.getValue();
            switch (nameValue.getName().trim()) {
                case "url" -> this.url = value;
                default -> throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }

}
