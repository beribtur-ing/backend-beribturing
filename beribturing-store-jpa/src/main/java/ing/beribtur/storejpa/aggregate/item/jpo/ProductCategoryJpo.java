package ing.beribtur.storejpa.aggregate.item.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.item.entity.ProductCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PRODUCT_CATEGORY")
public class ProductCategoryJpo extends DomainEntityJpo {

    @Column(nullable = false)
    private String name;

    private String description;

    private String iconUrl;

    private String parentId;
    
    @Column(nullable = false)
    private boolean active = true;

    public ProductCategoryJpo(ProductCategory productCategory) {
        //
        super(productCategory);
        BeanUtils.copyProperties(productCategory, this);
    }

    public ProductCategory toDomain() {
        //
        ProductCategory productCategory = new ProductCategory();
        BeanUtils.copyProperties(this, productCategory);
        return productCategory;
    }

    public static List<ProductCategory> toDomains(List<ProductCategoryJpo> jpos) {
        //
        return jpos.stream().map(ProductCategoryJpo::toDomain).toList();
    }
}