package ing.beribtur.aggregate.item.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.aggregate.payment.entity.vo.Discountable;
import ing.beribtur.aggregate.user.entity.Landee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends DomainEntity implements Discountable {
    //
    private String ownerId; // Reference to User (Lender)
    private String title;
    private String description;
    private String categoryId; // Reference to ProductCategory

    // Domain relationships
    private transient Landee owner;
    private transient ProductCategory category;
    private transient List<ProductVariant> variants;
}

