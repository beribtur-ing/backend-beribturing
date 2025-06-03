package ing.beribtur.storejpa.aggregate.item;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.item.entity.ProductCategory;
import ing.beribtur.aggregate.item.store.ProductCategoryStore;
import ing.beribtur.storejpa.aggregate.item.jpo.ProductCategoryJpo;
import ing.beribtur.storejpa.aggregate.item.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
public class ProductCategoryJpaStore implements ProductCategoryStore {
    //
    private final ProductCategoryRepository productCategoryJpaRepository;

    @Override
    public void create(ProductCategory productCategory) {
        //
        ProductCategoryJpo productCategoryJpo = new ProductCategoryJpo(productCategory);
        productCategoryJpaRepository.save(productCategoryJpo);
    }

    @Override
    public void createAll(List<ProductCategory> productCategorys) {
        //
        if (productCategorys == null) {
            return;
        }
        productCategorys.forEach(this::create);
    }

    @Override
    public ProductCategory retrieve(String id) {
        //
        Optional<ProductCategoryJpo> productCategoryJpo = productCategoryJpaRepository.findById(id);
        return productCategoryJpo.map(ProductCategoryJpo::toDomain).orElse(null);
    }

    @Override
    public List<ProductCategory> retrieveAll(List<String> productCategoryIds) {
        //
        Iterable<ProductCategoryJpo> allById = productCategoryJpaRepository.findAllById(productCategoryIds);
        return ProductCategoryJpo.toDomains(StreamSupport.stream(allById.spliterator(), false).collect(Collectors.toList()));
    }

    @Override
    public List<ProductCategory> retrieveList(Offset offset) {
        //
        Pageable pageable = PageRequest.of(offset.page(), offset.limit());
        return productCategoryJpaRepository.findAll(pageable).map(ProductCategoryJpo::toDomain).toList();
    }

    @Override
    public void update(ProductCategory productCategory) {
        //
        ProductCategoryJpo productCategoryJpo = new ProductCategoryJpo(productCategory);
        productCategoryJpaRepository.save(productCategoryJpo);
    }

    @Override
    public void delete(ProductCategory productCategory) {
        //
        productCategoryJpaRepository.deleteById(productCategory.getId());
    }

    @Override
    public void delete(String id) {
        //
        productCategoryJpaRepository.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        //
        Optional<ProductCategoryJpo> productCategoryJpo = productCategoryJpaRepository.findById(id);
        return productCategoryJpo.isPresent();
    }

    @Override
    public List<ProductCategory> retrieveByParentId(String parentId) {
        //
        return productCategoryJpaRepository.findByParentId(parentId).stream().map(ProductCategoryJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<ProductCategory> retrieveByNameContaining(String name) {
        //
        return productCategoryJpaRepository.findByNameContaining(name).stream().map(ProductCategoryJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public ProductCategory retrieveByName(String name) {
        //
        return productCategoryJpaRepository.findByName(name).map(ProductCategoryJpo::toDomain).orElse(null);
    }
}
