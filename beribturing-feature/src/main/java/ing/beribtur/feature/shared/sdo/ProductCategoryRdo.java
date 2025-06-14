package ing.beribtur.feature.shared.sdo;

import ing.beribtur.aggregate.item.entity.ProductCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductCategoryRdo {
    private ProductCategory category;
    private List<ProductCategoryRdo> subCategories;
}
