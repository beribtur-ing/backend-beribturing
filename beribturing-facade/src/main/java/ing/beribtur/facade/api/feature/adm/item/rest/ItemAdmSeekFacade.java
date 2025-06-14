package ing.beribtur.facade.api.feature.adm.item.rest;


import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.facade.api.feature.adm.item.query.FindProductCategoryRdoAdmQuery;
import ing.beribtur.facade.api.feature.adm.item.query.FindProductCategoryRdosAdmQuery;
import ing.beribtur.facade.api.feature.adm.item.query.FindProductRdoAdmQuery;
import ing.beribtur.facade.api.feature.adm.item.query.FindProductRdosAdmQuery;
import ing.beribtur.feature.shared.sdo.ProductCategoryRdo;
import ing.beribtur.feature.shared.sdo.ProductRdo;

import java.util.List;

public interface ItemAdmSeekFacade {
    //
    QueryResponse<List<ProductCategoryRdo>> findProductCategoryRdos(FindProductCategoryRdosAdmQuery query);
    QueryResponse<ProductCategoryRdo> findProductCategoryRdo(FindProductCategoryRdoAdmQuery query);
    QueryResponse<List<ProductRdo>> findProductRdos(FindProductRdosAdmQuery query);
    QueryResponse<ProductRdo> findProductRdo(FindProductRdoAdmQuery query);
}
