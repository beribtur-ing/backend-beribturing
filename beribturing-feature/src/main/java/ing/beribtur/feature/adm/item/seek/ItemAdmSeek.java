package ing.beribtur.feature.adm.item.seek;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.feature.shared.customstore.ProductCategoryCustomStore;
import ing.beribtur.feature.shared.customstore.ProductCustomStore;
import ing.beribtur.feature.shared.sdo.ProductCategoryRdo;
import ing.beribtur.feature.shared.sdo.ProductRdo;
import ing.beribtur.feature.shared.sdo.ProductSearchQdo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemAdmSeek {
    //
    private final ProductCategoryCustomStore productCategoryCustomStore;
    private final ProductCustomStore productCustomStore;

    public Page<ProductCategoryRdo> findProductCategoryRdos(String searchKeyword, Offset offset) {
        //
        return productCategoryCustomStore.findProductCategoryRdos(searchKeyword, offset);
    }

    public Page<ProductRdo> findProductRdos(ProductSearchQdo qdo, Offset offset) {
        //
        return productCustomStore.findProductRdos(qdo, offset);
    }

    public ProductCategoryRdo findProductCategoryRdo(String categoryId) {
        //
        return productCategoryCustomStore.findProductCategoryRdo(categoryId);
    }

    public ProductRdo findProductRdo(String productId) {
        //
        return productCustomStore.findProductRdo(productId);
    }
}
