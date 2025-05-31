package ing.beribtur.aggregate.item.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductCategoryCdo extends CreationDataObject {
    private String name;
    private String description;
    private String parentId; // Reference to parent category, if any
}
