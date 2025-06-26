package ing.beribtur.feature.adm.item.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.item.entity.sdo.ProductCdo;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductAdmRegCdo extends CreationDataObject {
    //
    private String ownerId;
    private String title;
    private String description;
    private String categoryId;

    public ProductCdo toCdo(long sequence) {
        //
        ProductCdo productCdo = ProductCdo.builder().build();
        BeanUtils.copyProperties(this, productCdo);
        productCdo.setSequence(sequence);
        return productCdo;
    }
}
