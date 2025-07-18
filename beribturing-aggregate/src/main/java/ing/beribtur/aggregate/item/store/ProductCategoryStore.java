package ing.beribtur.aggregate.item.store;


import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.item.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryStore {
    //
    void create(ProductCategory productCategory);
    void createAll(List<ProductCategory> productCategories);
    ProductCategory retrieve(String id);
    List<ProductCategory> retrieveAll(List<String> productCategoryIds);
    List<ProductCategory> retrieveList(Offset offset);
    void update(ProductCategory productCategory);
    void delete(ProductCategory productCategory);
    void delete(String id);
    boolean exists(String id);
}
