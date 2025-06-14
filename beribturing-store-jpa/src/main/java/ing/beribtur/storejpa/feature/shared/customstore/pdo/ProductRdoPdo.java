package ing.beribtur.storejpa.feature.shared.customstore.pdo;

import ing.beribtur.feature.shared.sdo.ProductRdo;
import ing.beribtur.feature.shared.sdo.ProductVariantRdo;
import ing.beribtur.storejpa.aggregate.item.jpo.ProductCategoryJpo;
import ing.beribtur.storejpa.aggregate.item.jpo.ProductJpo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
@Getter
@Setter
public class ProductRdoPdo {
    //
    private ProductJpo productJpo;
    private ProductCategoryJpo categoryJpo;

    public ProductRdo toRdo(Function<String, List<ProductVariantRdo>> variantFinder) {
        //
        List<ProductVariantRdo> variantRdos = variantFinder.apply(productJpo.getId());

        return ProductRdo.builder()
            .product(productJpo.toDomain())
            .category(categoryJpo != null ? categoryJpo.toDomain() : null)
            .variantRdos(variantRdos)
            .build();
    }
}
