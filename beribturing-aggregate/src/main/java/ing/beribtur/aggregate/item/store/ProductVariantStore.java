package ing.beribtur.aggregate.item.store;


import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.item.entity.ProductVariant;

import java.util.List;

public interface ProductVariantStore {
    //
    void create(ProductVariant productVariant);
    void createAll(List<ProductVariant> productVariants);
    ProductVariant retrieve(String id);
    List<ProductVariant> retrieveAll(List<String> productVariantIds);
    List<ProductVariant> retrieveList(Offset offset);
    void update(ProductVariant productVariant);
    void delete(ProductVariant productVariant);
    void delete(String id);
    boolean exists(String id);

    List<ProductVariant> retrieveByProductId(String productId);
}
