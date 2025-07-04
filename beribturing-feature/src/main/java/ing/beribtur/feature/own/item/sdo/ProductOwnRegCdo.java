package ing.beribtur.feature.own.item.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.item.entity.sdo.ProductCdo;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOwnRegCdo extends CreationDataObject {
    //
    private String title;
    private String description;
    private String categoryId;

    public ProductCdo toCdo(String ownerId, long sequence) {
        //
        ProductCdo productCdo = ProductCdo.builder().build();
        BeanUtils.copyProperties(this, productCdo);
        productCdo.setOwnerId(ownerId);
        productCdo.setSequence(sequence);
        return productCdo;
    }
}
