package ing.beribtur.storejpa.aggregate.item;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.item.entity.ProductVariant;
import ing.beribtur.aggregate.item.store.ProductVariantStore;
import ing.beribtur.storejpa.aggregate.item.jpo.ProductVariantJpo;
import ing.beribtur.storejpa.aggregate.item.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
public class ProductVariantJpaStore implements ProductVariantStore {
    //
    private final ProductVariantRepository productVariantRepository;

    @Override
    public void create(ProductVariant productVariant) {
        //
        ProductVariantJpo productVariantJpo = new ProductVariantJpo(productVariant);
        productVariantRepository.save(productVariantJpo);
    }

    @Override
    public void createAll(List<ProductVariant> productVariants) {
        //
        productVariantRepository.saveAll(productVariants.stream().map(ProductVariantJpo::new).collect(Collectors.toList()));
    }

    @Override
    public ProductVariant retrieve(String id) {
        //
        return productVariantRepository.findById(id)
            .map(ProductVariantJpo::toDomain)
            .orElse(null);
    }

    @Override
    public List<ProductVariant> retrieveAll(List<String> productVariantIds) {
        //
        Iterable<ProductVariantJpo> allById = productVariantRepository.findAllById(productVariantIds);
        return ProductVariantJpo.toDomains(StreamSupport.stream(allById.spliterator(), false).collect(Collectors.toList()));
    }

    @Override
    public List<ProductVariant> retrieveList(Offset offset) {
        //
        Pageable pageable = PageRequest.of(offset.page(), offset.limit());

        return productVariantRepository.findAll(pageable).map(ProductVariantJpo::toDomain).toList();
    }

    @Override
    public void update(ProductVariant productVariant) {
        //
        productVariantRepository.save(new ProductVariantJpo(productVariant));
    }

    @Override
    public void delete(ProductVariant productVariant) {
        //
        productVariantRepository.delete(new ProductVariantJpo(productVariant));
    }

    @Override
    public void delete(String id) {
        //
        productVariantRepository.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        //
        return productVariantRepository.existsById(id);
    }

    @Override
    public List<ProductVariant> retrieveByProductId(String productId) {
        //
        return productVariantRepository.findByProductId(productId).stream().map(ProductVariantJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<ProductVariant> retrieveByIsActive(boolean isActive) {
        //
        return productVariantRepository.findByIsActive(isActive).stream().map(ProductVariantJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<ProductVariant> retrieveByBrand(String brand) {
        //
        return productVariantRepository.findByBrand(brand).stream().map(ProductVariantJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<ProductVariant> retrieveByColor(String color) {
        //
        return productVariantRepository.findByColor(color).stream().map(ProductVariantJpo::toDomain).collect(Collectors.toList());
    }
}
