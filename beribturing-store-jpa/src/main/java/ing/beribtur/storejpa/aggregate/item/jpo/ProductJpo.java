package ing.beribtur.storejpa.aggregate.item.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.item.entity.Product;
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
@Table(name = "PRODUCT")
public class ProductJpo extends DomainEntityJpo {

    @Column(nullable = false)
    private String ownerId;

    @Column(nullable = false)
    private String title;

    private String description;

    private String categoryId;

    private long variantSequence;

    @Column(nullable = false)
    private boolean active = true;

    public ProductJpo(Product product) {
        //
        super(product);
        BeanUtils.copyProperties(product, this);
    }

    public Product toDomain() {
        //
        Product product = new Product();
        BeanUtils.copyProperties(this, product);
        return product;
    }

    public static List<Product> toDomains(List<ProductJpo> jpos) {
        //
        return jpos.stream().map(ProductJpo::toDomain).toList();
    }
}
