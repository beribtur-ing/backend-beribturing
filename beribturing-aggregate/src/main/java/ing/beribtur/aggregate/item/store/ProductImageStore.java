package ing.beribtur.aggregate.item.store;


import ing.beribtur.aggregate.item.entity.ProductImage;

import java.util.List;

public interface ProductImageStore {
    //
    ProductImage create(ProductImage productImage);
    ProductImage retrieve(String id);
    List<ProductImage> retrieveAll(List<String> ids);
    ProductImage update(ProductImage productImage);
    void delete(String id);
}
