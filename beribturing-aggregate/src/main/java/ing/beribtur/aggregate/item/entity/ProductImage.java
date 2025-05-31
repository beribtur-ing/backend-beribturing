package ing.beribtur.aggregate.item.entity;

import ing.beribtur.accent.domain.DomainEntity;


public class ProductImage extends DomainEntity {
    //
    private String variantId; // Reference to the product variant this image belongs to
    private String url;
    private int order;

    // Domain relationships
    private transient ProductVariant variant;
}
