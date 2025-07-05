package ing.beribtur.aggregate.item.store;


import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.item.entity.Product;

import java.util.List;

public interface ProductStore {
    //
    void create(Product product);
    void createAll(List<Product> products);
    Product retrieve(String id);
    List<Product> retrieveAll(List<String> productIds);
    List<Product> retrieveList(Offset offset);
    void update(Product product);
    void delete(Product product);
    void delete(String id);
    boolean exists(String id);

    List<Product> retrieveByCategoryId(String categoryId);

    // Additional active filtering methods (requested API)
    List<Product> retrieveByActive(boolean active);

    int countOfProductsByOwnerId(String username);
    
    // Count methods for statistics
    long countActiveProducts();
}
