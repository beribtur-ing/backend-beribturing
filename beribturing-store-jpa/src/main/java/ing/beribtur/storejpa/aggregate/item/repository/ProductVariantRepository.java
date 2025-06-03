package ing.beribtur.storejpa.aggregate.item.repository;

import ing.beribtur.storejpa.aggregate.item.jpo.ProductVariantJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariantJpo, String> {
    List<ProductVariantJpo> findByProductId(String productId);
    List<ProductVariantJpo> findByActive(boolean isActive);
    List<ProductVariantJpo> findByBrand(String brand);
    List<ProductVariantJpo> findByColor(String color);
}