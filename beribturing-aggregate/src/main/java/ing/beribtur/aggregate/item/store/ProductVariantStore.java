package ing.beribtur.aggregate.item.store;


import ing.beribtur.aggregate.item.entity.ProductVariant;

import java.util.List;

public interface ProductVariantStore {
    //
    void create(ProductVariant productVariant);
    ProductVariant retrieve(String id);
    List<ProductVariant> retrieveAll(List<String> ids);
    void update(ProductVariant productVariant);
    void delete(String id);
}
