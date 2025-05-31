package ing.beribtur.aggregate.item.store;


import ing.beribtur.aggregate.item.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryStore {
    //
    void create(ProductCategory productCategory);
    ProductCategory retrieve(String id);
    List<ProductCategory> retrieveAll(List<String> ids);
    void update(ProductCategory productCategory);
    void delete(String id);
}
