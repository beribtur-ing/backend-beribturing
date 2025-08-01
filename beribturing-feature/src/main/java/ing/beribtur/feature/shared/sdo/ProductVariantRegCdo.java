package ing.beribtur.feature.shared.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.item.entity.sdo.ProductVariantCdo;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariantRegCdo extends CreationDataObject {
    //
    private String productId;
    private String name;
    private String description;

    public ProductVariantCdo toCdo(long sequence) {
        //
        ProductVariantCdo productVariantCdo = ProductVariantCdo.builder().build();
        BeanUtils.copyProperties(this, productVariantCdo);
        productVariantCdo.setSequence(sequence);
        return productVariantCdo;
    }
}
