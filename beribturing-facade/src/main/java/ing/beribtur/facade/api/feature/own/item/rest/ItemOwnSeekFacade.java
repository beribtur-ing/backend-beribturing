package ing.beribtur.facade.api.feature.own.item.rest;


import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.facade.api.feature.own.item.query.FindProductCategoryRdoOwnQuery;
import ing.beribtur.facade.api.feature.own.item.query.FindProductCategoryRdosOwnQuery;
import ing.beribtur.facade.api.feature.own.item.query.FindProductRdoOwnQuery;
import ing.beribtur.facade.api.feature.own.item.query.FindProductRdosOwnQuery;
import ing.beribtur.feature.shared.sdo.ProductCategoryRdo;
import ing.beribtur.feature.shared.sdo.ProductRdo;

import java.util.List;

public interface ItemOwnSeekFacade {
    //
    QueryResponse<List<ProductCategoryRdo>> findProductCategoryRdos(FindProductCategoryRdosOwnQuery query);
    QueryResponse<ProductCategoryRdo> findProductCategoryRdo(FindProductCategoryRdoOwnQuery query);
    QueryResponse<List<ProductRdo>> findProductRdos(FindProductRdosOwnQuery query);
    QueryResponse<ProductRdo> findProductRdo(FindProductRdoOwnQuery query);
}
