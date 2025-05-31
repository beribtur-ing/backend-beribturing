package ing.beribtur.aggregate.rental.entity;


import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValueList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

    @Override
    protected void modifyAttributes(NameValueList var1) {

    }
}
