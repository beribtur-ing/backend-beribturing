package ing.beribtur.aggregate.rental.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.rental.entity.ItemConditionPhoto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemConditionPhotoCdo extends CreationDataObject {
    //
    private String checkId; // The ID of the ItemConditionCheck this photo belongs to
    private String url;
    private long sequence; // Sequence number for photos, starting from 1

    @Override
    public String genId() {
        return ItemConditionPhoto.genId(checkId, sequence);
    }
}
