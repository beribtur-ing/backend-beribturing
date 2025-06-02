package ing.beribtur.storejpa.aggregate.item;

import ing.beribtur.aggregate.item.entity.ProductImage;
import ing.beribtur.aggregate.item.store.ProductImageStore;
import ing.beribtur.storejpa.aggregate.item.jpo.ProductImageJpo;
import ing.beribtur.storejpa.aggregate.item.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductImageJpaStore implements ProductImageStore {
    //
    private final ProductImageRepository productImageRepository;

    @Override
    public void create(ProductImage productImage) {
        ProductImageJpo productImageJpo = new ProductImageJpo(productImage);

        productImageRepository.save(productImageJpo);
        productImage.setId(productImageJpo.getId());
    }

    @Override
    public ProductImage retrieve(String id) {
        ProductImageJpo productImageJpo = productImageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ProductImage not found: " + id));
        return productImageJpo.toDomain();
    }

    @Override
    public List<ProductImage> retrieveAll(List<String> ids) {
        List<ProductImageJpo> productImageJpos = productImageRepository.findAllById(ids);
        return productImageJpos.stream()
                .map(ProductImageJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void update(ProductImage productImage) {
        ProductImageJpo productImageJpo = productImageRepository.findById(productImage.getId())
                .orElseThrow(() -> new IllegalArgumentException("ProductImage not found: " + productImage.getId()));
        
        // Update the JPO with the domain entity's values
        ProductImageJpo updatedJpo = new ProductImageJpo(productImage);
        updatedJpo.setEntityVersion(productImageJpo.getEntityVersion());
        updatedJpo.setRegisteredBy(productImageJpo.getRegisteredBy());
        updatedJpo.setRegisteredOn(productImageJpo.getRegisteredOn());
        
        productImageRepository.save(updatedJpo);
    }

    @Override
    public void delete(String id) {
        productImageRepository.deleteById(id);
    }
    
    // Additional methods for specific queries
    public List<ProductImage> findByVariantId(String variantId) {
        return ProductImageJpo.toDomains(productImageRepository.findByVariantId(variantId));
    }
    
    public List<ProductImage> findByVariantIdOrderByOrderAsc(String variantId) {
        return ProductImageJpo.toDomains(productImageRepository.findByVariantIdOrderByOrderAsc(variantId));
    }
}