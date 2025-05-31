package ing.beribtur.aggregate.rental.entity;


import ing.beribtur.accent.domain.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemConditionPhoto extends DomainEntity {
    //
    private UUID checkId; // The ID of the ItemConditionCheck this photo belongs to
    private String url;

    // Domain relationships
    private transient ItemConditionCheck itemConditionCheck;
}
