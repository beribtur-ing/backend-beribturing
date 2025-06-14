package ing.beribtur.storejpa.feature.shared.customstore.pdo;

import ing.beribtur.feature.shared.sdo.ProductCategoryRdo;
import ing.beribtur.storejpa.aggregate.item.jpo.ProductCategoryJpo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
@Getter
@Setter
public class ProductCategoryRdoPdo {
    //
    private ProductCategoryJpo productCategoryJpo;

    public ProductCategoryRdo toRdo(Function<String, List<ProductCategoryRdo>> subCategoryFinder) {
        //
        List<ProductCategoryRdo> subCategories = subCategoryFinder.apply(productCategoryJpo.getId());

        return ProductCategoryRdo.builder()
            .category(productCategoryJpo.toDomain())
            .subCategories(subCategories)
            .build();
    }
}
