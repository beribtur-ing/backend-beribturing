package ing.beribtur.storejpa.aggregate.item.repository;

import ing.beribtur.storejpa.aggregate.item.jpo.ProductJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductJpo, String> {
    List<ProductJpo> findByCategoryId(String categoryId);
    List<ProductJpo> findByActive(boolean active);

    int countByOwnerId(String ownerId);
    
    // Count methods for statistics
    long countByActive(boolean active);
}
