package ing.beribtur.storejpa.aggregate.item.repository;

import ing.beribtur.storejpa.aggregate.item.jpo.ProductImageJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImageJpo, String> {
    List<ProductImageJpo> findByVariantId(String variantId);
    List<ProductImageJpo> findByVariantIdOrderByOrderAsc(String variantId);
}