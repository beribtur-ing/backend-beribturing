package ing.beribtur.feature.adm.item.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.item.entity.sdo.ProductCategoryCdo;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryRegCdo extends CreationDataObject {
    //
    private String name;
    private String description;
    private String parentId;

    public ProductCategoryCdo toCdo() {
        //
        ProductCategoryCdo productCategoryCdo = ProductCategoryCdo.builder().build();
        BeanUtils.copyProperties(this, productCategoryCdo);
        return productCategoryCdo;
    }
}
