package ing.beribtur.aggregate.item.entity;


import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.aggregate.payment.entity.vo.Discountable;

import java.util.List;

public class ProductCategory extends DomainEntity implements Discountable {
    //
    private String name;
    private String description;
    private String parentId; // Reference to parent category, if any

    // Domain relationships
    private transient List<ProductCategory> subCategories;
}
