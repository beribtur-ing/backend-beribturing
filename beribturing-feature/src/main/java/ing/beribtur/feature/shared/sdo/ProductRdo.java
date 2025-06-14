package ing.beribtur.feature.shared.sdo;

import ing.beribtur.aggregate.item.entity.Product;
import ing.beribtur.aggregate.item.entity.ProductCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductRdo {
    private Product product;
    private ProductCategory category;
    private List<ProductVariantRdo> variantRdos;
}
