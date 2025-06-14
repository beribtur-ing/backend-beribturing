package ing.beribtur.storejpa.aggregate.item;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.item.entity.ProductImage;
import ing.beribtur.aggregate.item.store.ProductImageStore;
import ing.beribtur.storejpa.aggregate.item.jpo.ProductImageJpo;
import ing.beribtur.storejpa.aggregate.item.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
public class ProductImageJpaStore implements ProductImageStore {
    //
    private final ProductImageRepository productImageRepository;

    @Override
    public void create(ProductImage productImage) {
        //
        ProductImageJpo productImageJpo = new ProductImageJpo(productImage);
        productImageRepository.save(productImageJpo);
    }

    @Override
    public void createAll(List<ProductImage> productImages) {
        //
        productImageRepository.saveAll(productImages.stream().map(ProductImageJpo::new).collect(Collectors.toList()));
    }

    @Override
    public ProductImage retrieve(String id) {
        //
        return productImageRepository.findById(id)
                .map(ProductImageJpo::toDomain)
                .orElse(null);
    }

    @Override
    public List<ProductImage> retrieveAll(List<String> productImageIds) {
        //
        Iterable<ProductImageJpo> allById = productImageRepository.findAllById(productImageIds);
        return ProductImageJpo.toDomains(StreamSupport.stream(allById.spliterator(), false).collect(Collectors.toList()));
    }

    @Override
    public List<ProductImage> retrieveList(Offset offset) {
        //
        Pageable pageable = PageRequest.of(offset.page(), offset.limit());

        return productImageRepository.findAll(pageable).map(ProductImageJpo::toDomain).toList();
    }

    @Override
    public void update(ProductImage productImage) {
        //
        productImageRepository.save(new ProductImageJpo(productImage));
    }

    @Override
    public void delete(ProductImage productImage) {
        //
        productImageRepository.delete(new ProductImageJpo(productImage));
    }

    @Override
    public void delete(String id) {
        //
        productImageRepository.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        //
        return productImageRepository.existsById(id);
    }

    @Override
    public List<ProductImage> retrieveByVariantIdOrderByOrderAsc(String variantId) {
        //
        return productImageRepository.findByVariantIdOrderByOrderAsc(variantId).stream().map(ProductImageJpo::toDomain).collect(Collectors.toList());
    }
}
