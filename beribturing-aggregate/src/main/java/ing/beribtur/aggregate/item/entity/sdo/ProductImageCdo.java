package ing.beribtur.aggregate.item.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.item.entity.ProductImage;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageCdo extends CreationDataObject {
    //
    private String variantId; // Reference to the product variant this image belongs to
    private String url;
    private int order;
    private long sequence;

    public String genId() {
        //
        return ProductImage.genId(variantId, sequence);
    }
}
