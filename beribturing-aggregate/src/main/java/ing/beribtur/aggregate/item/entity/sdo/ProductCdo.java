package ing.beribtur.aggregate.item.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.item.entity.Product;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCdo extends CreationDataObject {
    //
    private String ownerId; // Reference to User (Lender)
    private String title;
    private String description;
    private String categoryId; // Reference to ProductCategory
    private long sequence;

    public String genId() {
        //
        return Product.genId(ownerId, sequence);
    }
}
