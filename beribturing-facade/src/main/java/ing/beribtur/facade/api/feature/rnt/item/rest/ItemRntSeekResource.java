package ing.beribtur.facade.api.feature.rnt.item.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.accent.util.QueryResponseUtil;
import ing.beribtur.facade.api.feature.rnt.item.query.FindProductCategoryRdoRntQuery;
import ing.beribtur.facade.api.feature.rnt.item.query.FindProductCategoryRdosRntQuery;
import ing.beribtur.facade.api.feature.rnt.item.query.FindProductRdoRntQuery;
import ing.beribtur.facade.api.feature.rnt.item.query.FindProductRdosRntQuery;
import ing.beribtur.feature.rnt.item.seek.ItemRntSeek;
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
@RequestMapping("/feature/renter/item")
public class ItemRntSeekResource implements ItemRntSeekFacade {
    //
    private final ItemRntSeek itemRntSeek;

    @Override
    @PostMapping("/find-product-category-rdos/query")
    public QueryResponse<List<ProductCategoryRdo>> findProductCategoryRdos(@RequestBody FindProductCategoryRdosRntQuery query) {
        //
        query.validate();
        Page<ProductCategoryRdo> responsePage = itemRntSeek.findProductCategoryRdos(query.getSearchKeyword(), query.getOffset());
        return QueryResponseUtil.fromPage(responsePage);
    }

    @Override
    @PostMapping("/find-product-category-rdo/query")
    public QueryResponse<ProductCategoryRdo> findProductCategoryRdo(@RequestBody FindProductCategoryRdoRntQuery query) {
        //
        query.validate();
        String categoryId = query.getCategoryId();
        ProductCategoryRdo response = itemRntSeek.findProductCategoryRdo(categoryId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-product-rdos/query")
    public QueryResponse<List<ProductRdo>> findProductRdos(@RequestBody FindProductRdosRntQuery query) {
        //
        query.validate();
        ProductSearchQdo qdo = query.toQdo();
        Page<ProductRdo> responsePage = itemRntSeek.findProductRdos(qdo, query.getOffset());
        return QueryResponseUtil.fromPage(responsePage);
    }

    @Override
    @PostMapping("/find-product-rdo/query")
    public QueryResponse<ProductRdo> findProductRdo(@RequestBody FindProductRdoRntQuery query) {
        //
        query.validate();
        String productId = query.getProductId();
        ProductRdo response = itemRntSeek.findProductRdo(productId);
        return new QueryResponse<>(response);
    }
}
