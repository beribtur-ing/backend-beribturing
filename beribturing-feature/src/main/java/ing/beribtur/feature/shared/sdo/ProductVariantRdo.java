package ing.beribtur.feature.shared.sdo;

import ing.beribtur.aggregate.item.entity.ProductImage;
import ing.beribtur.aggregate.item.entity.ProductVariant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductVariantRdo {
    private ProductVariant variant;
    private List<ProductImage> images;
}
