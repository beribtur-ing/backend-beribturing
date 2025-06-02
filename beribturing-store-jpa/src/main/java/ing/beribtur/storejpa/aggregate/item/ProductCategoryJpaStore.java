package ing.beribtur.storejpa.aggregate.item;

import ing.beribtur.aggregate.item.entity.ProductCategory;
import ing.beribtur.aggregate.item.store.ProductCategoryStore;
import ing.beribtur.storejpa.aggregate.item.jpo.ProductCategoryJpo;
import ing.beribtur.storejpa.aggregate.item.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductCategoryJpaStore implements ProductCategoryStore {
    //
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public void create(ProductCategory productCategory) {
        ProductCategoryJpo productCategoryJpo = new ProductCategoryJpo(productCategory);

        productCategoryRepository.save(productCategoryJpo);
        productCategory.setId(productCategoryJpo.getId());
    }

    @Override
    public ProductCategory retrieve(String id) {
        ProductCategoryJpo productCategoryJpo = productCategoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ProductCategory not found: " + id));
        return productCategoryJpo.toDomain();
    }

    @Override
    public List<ProductCategory> retrieveAll(List<String> ids) {
        List<ProductCategoryJpo> productCategoryJpos = productCategoryRepository.findAllById(ids);
        return productCategoryJpos.stream()
                .map(ProductCategoryJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void update(ProductCategory productCategory) {
        ProductCategoryJpo productCategoryJpo = productCategoryRepository.findById(productCategory.getId())
                .orElseThrow(() -> new IllegalArgumentException("ProductCategory not found: " + productCategory.getId()));
        
        // Update the JPO with the domain entity's values
        ProductCategoryJpo updatedJpo = new ProductCategoryJpo(productCategory);
        updatedJpo.setEntityVersion(productCategoryJpo.getEntityVersion());
        updatedJpo.setRegisteredBy(productCategoryJpo.getRegisteredBy());
        updatedJpo.setRegisteredOn(productCategoryJpo.getRegisteredOn());
        
        productCategoryRepository.save(updatedJpo);
    }

    @Override
    public void delete(String id) {
        productCategoryRepository.deleteById(id);
    }
    
    // Additional methods for specific queries
    public List<ProductCategory> findByParentId(String parentId) {
        return ProductCategoryJpo.toDomains(productCategoryRepository.findByParentId(parentId));
    }
    
    public List<ProductCategory> findByNameContaining(String name) {
        return ProductCategoryJpo.toDomains(productCategoryRepository.findByNameContaining(name));
    }
    
    public ProductCategory findByName(String name) {
        ProductCategoryJpo productCategoryJpo = productCategoryRepository.findByName(name);
        return productCategoryJpo != null ? productCategoryJpo.toDomain() : null;
    }
}