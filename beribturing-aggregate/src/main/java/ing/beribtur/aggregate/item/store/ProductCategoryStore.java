package ing.beribtur.aggregate.item.store;


import ing.beribtur.aggregate.item.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryStore {
    //
    ProductCategory create(ProductCategory productCategory);
    ProductCategory retrieve(String id);
    List<ProductCategory> retrieveAll(List<String> ids);
    ProductCategory update(ProductCategory productCategory);
    void delete(String id);
}
