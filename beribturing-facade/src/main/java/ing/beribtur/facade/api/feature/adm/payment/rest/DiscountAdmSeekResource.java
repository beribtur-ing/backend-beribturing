package ing.beribtur.facade.api.feature.adm.payment.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.payment.entity.Discount;
import ing.beribtur.facade.api.feature.adm.payment.query.*;
import ing.beribtur.feature.adm.payment.seek.DiscountAdmSeek;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feature/admin/payment/discount")
@RequiredArgsConstructor
public class DiscountAdmSeekResource implements DiscountAdmSeekFacade {
    private final DiscountAdmSeek discountAdmSeek;

    @Override
    @PostMapping("/find/query")
    public QueryResponse<Discount> findDiscount(@RequestBody FindDiscountAdmQuery query) {
        query.validate();
        var discount = discountAdmSeek.findDiscount(query.getDiscountId());
        return new QueryResponse<>(discount);
    }

    @Override
    @PostMapping("/find-multiple/query")
    public QueryResponse<List<Discount>> findDiscounts(@RequestBody FindDiscountsAdmQuery query) {
        query.validate();
        List<Discount> discounts = List.of();

        if (query.getDiscountIds() != null) {
            discounts = discountAdmSeek.findDiscounts(query.getDiscountIds());
        } else if (query.getOffset() != null) {
            discounts = discountAdmSeek.findDiscounts(query.getOffset());
        } else if (query.getActive() != null) {
            discounts = discountAdmSeek.findActiveDiscounts();
        } else if (query.getScope() != null) {
            discounts = discountAdmSeek.findByScope(query.getScope());
        } else if (query.getTargetId() != null) {
            discounts = discountAdmSeek.findByTargetId(query.getTargetId());
        }

        return new QueryResponse<>(discounts);
    }

    @Override
    @PostMapping("/find-expired/query")
    public QueryResponse<List<Discount>> findExpiredDiscounts(@RequestBody FindExpiredDiscountsAdmQuery query) {
        var discounts = discountAdmSeek.findExpiredDiscounts();
        return new QueryResponse<>(discounts);
    }

    @Override
    @PostMapping("/find-valid/query")
    public QueryResponse<List<Discount>> findValidDiscounts(@RequestBody FindValidDiscountsAdmQuery query) {
        var discounts = discountAdmSeek.findValidDiscounts();
        return new QueryResponse<>(discounts);
    }

    @Override
    @PostMapping("/find-applicable/query")
    public QueryResponse<List<Discount>> findApplicableDiscounts(@RequestBody FindApplicableDiscountsAdmQuery query) {
        query.validate();
        var discounts = discountAdmSeek.findApplicableDiscounts(query.getTargetId(), query.getScope());
        return new QueryResponse<>(discounts);
    }
}
