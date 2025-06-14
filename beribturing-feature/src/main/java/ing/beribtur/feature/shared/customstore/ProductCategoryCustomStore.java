package ing.beribtur.feature.shared.customstore;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.feature.shared.sdo.ProductCategoryRdo;
import org.springframework.data.domain.Page;

public interface ProductCategoryCustomStore {
    //
    Page<ProductCategoryRdo> findProductCategoryRdos(String searchKeyword, Offset offset);
    Page<ProductCategoryRdo> findProductCategoryRdos(String searchKeyword, Offset offset, boolean activeOnly);
    ProductCategoryRdo findProductCategoryRdo(String categoryId);
    ProductCategoryRdo findProductCategoryRdo(String categoryId, boolean activeOnly);
}
