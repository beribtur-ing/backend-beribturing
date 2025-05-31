package ing.beribtur.aggregate.item.entity;


import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.payment.entity.vo.Discountable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory extends DomainEntity implements Discountable {
    //
    private String name;
    private String description;
    private String parentId; // Reference to parent category, if any

    // Domain relationships
    private transient List<ProductCategory> subCategories;

    @Override
    protected void modifyAttributes(NameValueList var1) {

    }
}
