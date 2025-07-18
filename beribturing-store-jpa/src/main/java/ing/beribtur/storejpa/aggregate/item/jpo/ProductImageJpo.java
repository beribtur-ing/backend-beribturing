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

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PRODUCT_IMAGE")
public class ProductImageJpo extends DomainEntityJpo {

    @Column(nullable = false)
    private String variantId;

    //@Column(nullable = false)
    private String filename;

    //@Column(nullable = false)
    private String originalFilename;

    @Column(nullable = false, columnDefinition = "varchar(500)")
    private String url;

    @Column(name = "\"order\"")
    private int order;

    @Column(nullable = false)
    private boolean active = true;

    private LocalDateTime expiresAt;

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
