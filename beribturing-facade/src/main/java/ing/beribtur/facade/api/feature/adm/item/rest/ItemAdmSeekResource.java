package ing.beribtur.facade.api.feature.adm.item.rest;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.accent.util.QueryResponseUtil;
import ing.beribtur.facade.api.feature.adm.item.query.FindProductCategoryRdoAdmQuery;
import ing.beribtur.facade.api.feature.adm.item.query.FindProductCategoryRdosAdmQuery;
import ing.beribtur.facade.api.feature.adm.item.query.FindProductRdoAdmQuery;
import ing.beribtur.facade.api.feature.adm.item.query.FindProductRdosAdmQuery;
import ing.beribtur.feature.adm.item.seek.ItemAdmSeek;
import ing.beribtur.feature.shared.sdo.ProductCategoryRdo;
import ing.beribtur.feature.shared.sdo.ProductRdo;
import ing.beribtur.feature.shared.sdo.ProductSearchQdo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/admin/item")
public class ItemAdmSeekResource implements ItemAdmSeekFacade {
    //
    private final ItemAdmSeek itemAdmSeek;
    @Override
    @PostMapping("/find-product-category-rdos/query")
    public QueryResponse<List<ProductCategoryRdo>> findProductCategoryRdos(@RequestBody FindProductCategoryRdosAdmQuery query) {
        //
        query.validate();
        String searchKeyword = query.getSearchKeyword();
        Offset offset = query.getOffset();
        Page<ProductCategoryRdo> response = itemAdmSeek.findProductCategoryRdos(searchKeyword, offset);
        return QueryResponseUtil.fromPage(response);
    }

    @Override
    @PostMapping("/find-product-category-rdo/query")
    public QueryResponse<ProductCategoryRdo> findProductCategoryRdo(@RequestBody FindProductCategoryRdoAdmQuery query) {
        //
        query.validate();
        String categoryId = query.getCategoryId();
        ProductCategoryRdo response = itemAdmSeek.findProductCategoryRdo(categoryId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-product-rdos/query")
    public QueryResponse<List<ProductRdo>> findProductRdos(@RequestBody FindProductRdosAdmQuery query) {
        //
        query.validate();
        ProductSearchQdo qdo = query.toQdo();
        Offset offset = query.getOffset();
        Page<ProductRdo> response = itemAdmSeek.findProductRdos(qdo, offset);
        return QueryResponseUtil.fromPage(response);
    }

    @Override
    @PostMapping("/find-product-rdo/query")
    public QueryResponse<ProductRdo> findProductRdo(@RequestBody FindProductRdoAdmQuery query) {
        //
        query.validate();
        String productId = query.getProductId();
        ProductRdo response = itemAdmSeek.findProductRdo(productId);
        return new QueryResponse<>(response);
    }
}
