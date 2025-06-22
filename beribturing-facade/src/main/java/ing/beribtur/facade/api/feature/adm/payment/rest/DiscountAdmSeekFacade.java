package ing.beribtur.facade.api.feature.adm.payment.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.payment.entity.Discount;
import ing.beribtur.facade.api.feature.adm.payment.query.*;

import java.util.List;

public interface DiscountAdmSeekFacade {
    QueryResponse<Discount> findDiscount(FindDiscountAdmQuery query);
    QueryResponse<List<Discount>> findDiscounts(FindDiscountsAdmQuery query);
    QueryResponse<List<Discount>> findExpiredDiscounts(FindExpiredDiscountsAdmQuery query);
    QueryResponse<List<Discount>> findValidDiscounts(FindValidDiscountsAdmQuery query);
    QueryResponse<List<Discount>> findApplicableDiscounts(FindApplicableDiscountsAdmQuery query);
}