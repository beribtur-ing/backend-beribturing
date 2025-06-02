package ing.beribtur.storejpa.aggregate.item.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.item.entity.ProductImage;
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
@Table(name = "PRODUCT_IMAGE")
public class ProductImageJpo extends DomainEntityJpo {

    @Column(nullable = false)
    private String variantId;
    
    @Column(nullable = false)
    private String url;
    
    private int order;

    public ProductImageJpo(ProductImage productImage) {
        //
        super(productImage);
        BeanUtils.copyProperties(productImage, this);
    }

    public ProductImage toDomain() {
        //
        ProductImage productImage = new ProductImage();
        BeanUtils.copyProperties(this, productImage);
        return productImage;
    }

    public static List<ProductImage> toDomains(List<ProductImageJpo> jpos) {
        //
        return jpos.stream().map(ProductImageJpo::toDomain).toList();
    }
}