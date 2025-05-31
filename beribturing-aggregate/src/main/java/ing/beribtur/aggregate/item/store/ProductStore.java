package ing.beribtur.aggregate.item.store;


import ing.beribtur.aggregate.item.entity.Product;

import java.util.List;

public interface ProductStore {
    //
    void create(Product product);
    Product retrieve(String id);
    List<Product> retrieveAll(List<String> ids);
    void update(Product product);
    void delete(String id);
}
