package ing.beribtur.storejpa.aggregate.item;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.item.entity.Product;
import ing.beribtur.aggregate.item.entity.Product;
import ing.beribtur.aggregate.item.store.ProductStore;
import ing.beribtur.storejpa.aggregate.item.jpo.ProductJpo;
import ing.beribtur.storejpa.aggregate.item.jpo.ProductJpo;
import ing.beribtur.storejpa.aggregate.item.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
public class ProductJpaStore implements ProductStore {
    //
    private final ProductRepository productRepository;

    @Override
    public void create(Product product) {
        //
        ProductJpo productJpo = new ProductJpo(product);
        productRepository.save(productJpo);
    }

    @Override
    public void createAll(List<Product> products) {
        //
        productRepository.saveAll(products.stream().map(ProductJpo::new).collect(Collectors.toList()));
    }

    @Override
    public Product retrieve(String id) {
        //
        return productRepository.findById(id)
            .map(ProductJpo::toDomain)
            .orElse(null);
    }

    @Override
    public List<Product> retrieveAll(List<String> productIds) {
        //
        Iterable<ProductJpo> allById = productRepository.findAllById(productIds);
        return ProductJpo.toDomains(StreamSupport.stream(allById.spliterator(), false).collect(Collectors.toList()));
    }

    @Override
    public List<Product> retrieveList(Offset offset) {
        //
        Pageable pageable = PageRequest.of(offset.page(), offset.limit());

        return productRepository.findAll(pageable).map(ProductJpo::toDomain).toList();
    }

    @Override
    public void update(Product product) {
        //
        productRepository.save(new ProductJpo(product));
    }

    @Override
    public void delete(Product product) {
        //
        productRepository.delete(new ProductJpo(product));
    }

    @Override
    public void delete(String id) {
        //
        productRepository.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        //
        return productRepository.existsById(id);
    }

    @Override
    public List<Product> retrieveByOwnerId(String ownerId) {
        //
        return productRepository.findByOwnerId(ownerId).stream().map(ProductJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Product> retrieveByCategoryId(String categoryId) {
        //
        return productRepository.findByCategoryId(categoryId).stream().map(ProductJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Product> retrieveByTitleContaining(String title) {
        //
        return productRepository.findByTitleContaining(title).stream().map(ProductJpo::toDomain).collect(Collectors.toList());
    }
}
