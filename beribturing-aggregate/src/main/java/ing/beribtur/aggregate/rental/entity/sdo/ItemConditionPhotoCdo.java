package ing.beribtur.aggregate.rental.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemConditionPhotoCdo extends CreationDataObject {
    //
    private String checkId; // The ID of the ItemConditionCheck this photo belongs to
    private String url;
}
