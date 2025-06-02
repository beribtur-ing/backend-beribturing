package ing.beribtur.storejpa.aggregate.item.repository;

import ing.beribtur.storejpa.aggregate.item.jpo.ProductCategoryJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategoryJpo, String> {
    List<ProductCategoryJpo> findByParentId(String parentId);
    List<ProductCategoryJpo> findByNameContaining(String name);
    ProductCategoryJpo findByName(String name);
}