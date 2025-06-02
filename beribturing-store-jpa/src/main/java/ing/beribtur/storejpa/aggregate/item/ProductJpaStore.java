package ing.beribtur.storejpa.aggregate.item;

import ing.beribtur.aggregate.item.entity.Product;
import ing.beribtur.aggregate.item.store.ProductStore;
import ing.beribtur.storejpa.aggregate.item.jpo.ProductJpo;
import ing.beribtur.storejpa.aggregate.item.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductJpaStore implements ProductStore {
    //
    private final ProductRepository productRepository;

    @Override
    public void create(Product product) {
        ProductJpo productJpo = new ProductJpo(product);

        productRepository.save(productJpo);
        product.setId(productJpo.getId());
    }

    @Override
    public Product retrieve(String id) {
        ProductJpo productJpo = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
        return productJpo.toDomain();
    }

    @Override
    public List<Product> retrieveAll(List<String> ids) {
        List<ProductJpo> productJpos = productRepository.findAllById(ids);
        return productJpos.stream()
                .map(ProductJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Product product) {
        ProductJpo productJpo = productRepository.findById(product.getId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + product.getId()));
        
        // Update the JPO with the domain entity's values
        ProductJpo updatedJpo = new ProductJpo(product);
        updatedJpo.setEntityVersion(productJpo.getEntityVersion());
        updatedJpo.setRegisteredBy(productJpo.getRegisteredBy());
        updatedJpo.setRegisteredOn(productJpo.getRegisteredOn());
        
        productRepository.save(updatedJpo);
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }
    
    // Additional methods for specific queries
    public List<Product> findByOwnerId(String ownerId) {
        return ProductJpo.toDomains(productRepository.findByOwnerId(ownerId));
    }
    
    public List<Product> findByCategoryId(String categoryId) {
        return ProductJpo.toDomains(productRepository.findByCategoryId(categoryId));
    }
    
    public List<Product> findByTitleContaining(String title) {
        return ProductJpo.toDomains(productRepository.findByTitleContaining(title));
    }
}