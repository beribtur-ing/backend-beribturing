package ing.beribtur.facade.api.feature.own.item.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.accent.util.QueryResponseUtil;
import ing.beribtur.facade.api.feature.own.item.query.FindProductCategoryRdoOwnQuery;
import ing.beribtur.facade.api.feature.own.item.query.FindProductCategoryRdosOwnQuery;
import ing.beribtur.facade.api.feature.own.item.query.FindProductRdoOwnQuery;
import ing.beribtur.facade.api.feature.own.item.query.FindProductRdosOwnQuery;
import ing.beribtur.feature.own.item.seek.ItemOwnSeek;
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
@RequestMapping("/feature/owner/item")
public class ItemOwnSeekResource implements ItemOwnSeekFacade {
    //
    private final ItemOwnSeek itemOwnSeek;

    @Override
    @PostMapping("/find-product-category-rdos/query")
    public QueryResponse<List<ProductCategoryRdo>> findProductCategoryRdos(@RequestBody FindProductCategoryRdosOwnQuery query) {
        //
        query.validate();
        Page<ProductCategoryRdo> responsePage = itemOwnSeek.findProductCategoryRdos(query.getSearchKeyword(), query.getOffset());
        return QueryResponseUtil.fromPage(responsePage);
    }

    @Override
    @PostMapping("/find-product-category-rdo/query")
    public QueryResponse<ProductCategoryRdo> findProductCategoryRdo(@RequestBody FindProductCategoryRdoOwnQuery query) {
        //
        query.validate();
        String categoryId = query.getCategoryId();
        ProductCategoryRdo response = itemOwnSeek.findProductCategoryRdo(categoryId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-product-rdos/query")
    public QueryResponse<List<ProductRdo>> findProductRdos(@RequestBody FindProductRdosOwnQuery query) {
        //
        query.validate();
        ProductSearchQdo qdo = query.toQdo();
        Page<ProductRdo> responsePage = itemOwnSeek.findProductRdos(qdo, query.getOffset());
        return QueryResponseUtil.fromPage(responsePage);
    }

    @Override
    @PostMapping("/find-product-rdo/query")
    public QueryResponse<ProductRdo> findProductRdo(@RequestBody FindProductRdoOwnQuery query) {
        //
        query.validate();
        String productId = query.getProductId();
        ProductRdo response = itemOwnSeek.findProductRdo(productId);
        return new QueryResponse<>(response);
    }
}
