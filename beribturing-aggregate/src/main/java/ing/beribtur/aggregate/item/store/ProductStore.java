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

    List<Product> retrieveByOwnerId(String ownerId);
    List<Product> retrieveByCategoryId(String categoryId);
    List<Product> retrieveByTitleContaining(String title);
}
