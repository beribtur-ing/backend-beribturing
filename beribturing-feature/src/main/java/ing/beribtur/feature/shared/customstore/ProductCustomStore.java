package ing.beribtur.feature.shared.customstore;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.feature.shared.sdo.ProductRdo;
import ing.beribtur.feature.shared.sdo.ProductSearchQdo;
import org.springframework.data.domain.Page;

public interface ProductCustomStore {
    //
    Page<ProductRdo> findProductRdos(ProductSearchQdo qdo, Offset offset);
    ProductRdo findProductRdo(String productId);
}
