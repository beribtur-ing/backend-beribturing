package ing.beribtur.aggregate.item.store;


import ing.beribtur.aggregate.item.entity.Product;

import java.util.List;

public interface ProductStore {
    //
    Product create(Product product);
    Product retrieve(String id);
    List<Product> retrieveAll(List<String> ids);
    Product update(Product product);
    void delete(String id);
}
