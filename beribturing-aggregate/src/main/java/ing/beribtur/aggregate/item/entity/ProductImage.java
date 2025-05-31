package ing.beribtur.aggregate.item.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValueList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage extends DomainEntity {
    //
    private String variantId; // Reference to the product variant this image belongs to
    private String url;
    private int order;

    // Domain relationships
    private transient ProductVariant variant;

    @Override
    protected void modifyAttributes(NameValueList var1) {

    }
}
