package ing.beribtur.aggregate.item.logic;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.util.Entities;
import ing.beribtur.aggregate.item.entity.Product;
import ing.beribtur.aggregate.item.entity.sdo.ProductCdo;
import ing.beribtur.aggregate.item.store.ProductStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductLogic {
    //
    private final ProductStore productStore;

    public String registerProduct(ProductCdo productCdo) {
        //
        Product product = new Product(productCdo);
        if (productStore.exists(product.getId())) {
            throw new IllegalArgumentException("product already exists. " + product.getId());
        }
        productStore.create(product);
        return product.getId();
    }

    public List<String> registerProducts(List<ProductCdo> productCdos) {
        //
        return productCdos.stream().map(this::registerProduct).collect(Collectors.toList());
    }

    public Product findProduct(String productId) {
        //
        Product product = productStore.retrieve(productId);
        if (product == null) {
            throw new NoSuchElementException("Product id: " + productId);
        }
        return product;
    }

    public List<Product> findProducts(Offset offset) {
        //
        return productStore.retrieveList(offset);
    }

    public List<Product> findActiveProducts(Offset offset) {
        //
        return productStore.retrieveByActive(true);
    }

    public void modifyProduct(String productId, NameValueList nameValues) {
        //
        Product product = findProduct(productId);
        product.modify(nameValues);
        productStore.update(product);
    }

    public void modifyProduct(Product product) {
        //
        Product oldProduct = findProduct(product.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldProduct, product);
        if (!nameValues.list().isEmpty()) {
            modifyProduct(product.getId(), nameValues);
        }
    }

    public void removeProduct(String productId) {
        //
        Product product = findProduct(productId);
        productStore.delete(product);
    }

    public boolean existsProduct(String productId) {
        //
        return productStore.exists(productId);
    }

    public List<Product> findByCategoryId(String categoryId) {
        //
        return productStore.retrieveByCategoryId(categoryId);
    }

    public long nextVariantSequence(String productId) {
        //
        Product product = findProduct(productId);
        long sequence = product.getVariantSequence();
        product.setVariantSequence(sequence + 1);
        productStore.update(product);
        return sequence;
    }

    public int countOwnedProducts(String ownerId) {
        //
        return productStore.countOfProductsByOwnerId(ownerId);
    }
}
