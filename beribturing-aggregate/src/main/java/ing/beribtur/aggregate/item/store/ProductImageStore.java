package ing.beribtur.aggregate.item.store;


import ing.beribtur.aggregate.item.entity.ProductImage;

import java.util.List;

public interface ProductImageStore {
    //
    void create(ProductImage productImage);
    ProductImage retrieve(String id);
    List<ProductImage> retrieveAll(List<String> ids);
    void update(ProductImage productImage);
    void delete(String id);
}
