package ing.beribtur.storejpa.aggregate.item;

import ing.beribtur.aggregate.item.entity.ProductVariant;
import ing.beribtur.aggregate.item.store.ProductVariantStore;
import ing.beribtur.storejpa.aggregate.item.jpo.ProductVariantJpo;
import ing.beribtur.storejpa.aggregate.item.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductVariantJpaStore implements ProductVariantStore {
    //
    private final ProductVariantRepository productVariantRepository;

    @Override
    public void create(ProductVariant productVariant) {
        ProductVariantJpo productVariantJpo = new ProductVariantJpo(productVariant);

        productVariantRepository.save(productVariantJpo);
        productVariant.setId(productVariantJpo.getId());
    }

    @Override
    public ProductVariant retrieve(String id) {
        ProductVariantJpo productVariantJpo = productVariantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ProductVariant not found: " + id));
        return productVariantJpo.toDomain();
    }

    @Override
    public List<ProductVariant> retrieveAll(List<String> ids) {
        List<ProductVariantJpo> productVariantJpos = productVariantRepository.findAllById(ids);
        return productVariantJpos.stream()
                .map(ProductVariantJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void update(ProductVariant productVariant) {
        ProductVariantJpo productVariantJpo = productVariantRepository.findById(productVariant.getId())
                .orElseThrow(() -> new IllegalArgumentException("ProductVariant not found: " + productVariant.getId()));
        
        // Update the JPO with the domain entity's values
        ProductVariantJpo updatedJpo = new ProductVariantJpo(productVariant);
        updatedJpo.setEntityVersion(productVariantJpo.getEntityVersion());
        updatedJpo.setRegisteredBy(productVariantJpo.getRegisteredBy());
        updatedJpo.setRegisteredOn(productVariantJpo.getRegisteredOn());
        
        productVariantRepository.save(updatedJpo);
    }

    @Override
    public void delete(String id) {
        productVariantRepository.deleteById(id);
    }
    
    // Additional methods for specific queries
    public List<ProductVariant> findByProductId(String productId) {
        return ProductVariantJpo.toDomains(productVariantRepository.findByProductId(productId));
    }
    
    public List<ProductVariant> findByIsActive(boolean isActive) {
        return ProductVariantJpo.toDomains(productVariantRepository.findByIsActive(isActive));
    }
    
    public List<ProductVariant> findByBrand(String brand) {
        return ProductVariantJpo.toDomains(productVariantRepository.findByBrand(brand));
    }
    
    public List<ProductVariant> findByColor(String color) {
        return ProductVariantJpo.toDomains(productVariantRepository.findByColor(color));
    }
}