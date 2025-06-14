package ing.beribtur.facade.api.feature.rnt.item.rest;


import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.facade.api.feature.rnt.item.query.FindProductCategoryRdoRntQuery;
import ing.beribtur.facade.api.feature.rnt.item.query.FindProductCategoryRdosRntQuery;
import ing.beribtur.facade.api.feature.rnt.item.query.FindProductRdoRntQuery;
import ing.beribtur.facade.api.feature.rnt.item.query.FindProductRdosRntQuery;
import ing.beribtur.feature.shared.sdo.ProductCategoryRdo;
import ing.beribtur.feature.shared.sdo.ProductRdo;

import java.util.List;

public interface ItemRntSeekFacade {
    //
    QueryResponse<List<ProductCategoryRdo>> findProductCategoryRdos(FindProductCategoryRdosRntQuery query);
    QueryResponse<ProductCategoryRdo> findProductCategoryRdo(FindProductCategoryRdoRntQuery query);
    QueryResponse<List<ProductRdo>> findProductRdos(FindProductRdosRntQuery query);
    QueryResponse<ProductRdo> findProductRdo(FindProductRdoRntQuery query);
}
