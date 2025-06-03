package ing.beribtur.aggregate.item.store;


import ing.beribtur.aggregate.item.entity.ProductImage;
import ing.beribtur.accent.message.Offset;

import java.util.List;

public interface ProductImageStore {
    //
    void create(ProductImage productImage);
    void createAll(List<ProductImage> productImages);
    ProductImage retrieve(String id);
    List<ProductImage> retrieveAll(List<String> productImageIds);
    List<ProductImage> retrieveList(Offset offset);
    void update(ProductImage productImage);
    void delete(ProductImage productImage);
    void delete(String id);
    boolean exists(String id);

    List<ProductImage> retrieveByVariantId(String variantId);
    List<ProductImage> retrieveByVariantIdOrderByOrderAsc(String variantId);
}
