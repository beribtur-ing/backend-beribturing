package ing.beribtur.facade.api.feature.adm.payment.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.facade.api.feature.adm.payment.query.*;
import ing.beribtur.facade.api.feature.adm.payment.sdo.DiscountRdo;

import java.util.List;

public interface DiscountAdmSeekFacade {
    QueryResponse<DiscountRdo> findDiscount(FindDiscountAdmQuery query);
    QueryResponse<List<DiscountRdo>> findDiscounts(FindDiscountsAdmQuery query);
    QueryResponse<List<DiscountRdo>> findExpiredDiscounts(FindExpiredDiscountsAdmQuery query);
    QueryResponse<List<DiscountRdo>> findValidDiscounts(FindValidDiscountsAdmQuery query);
    QueryResponse<List<DiscountRdo>> findApplicableDiscounts(FindApplicableDiscountsAdmQuery query);
} 