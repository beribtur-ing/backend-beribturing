package ing.beribtur.facade.api.feature.adm.payment.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.facade.api.feature.adm.payment.query.*;
import ing.beribtur.facade.api.feature.adm.payment.sdo.DiscountRdo;
import ing.beribtur.feature.adm.payment.seek.DiscountAdmSeek;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/feature/admin/payment/discount")
@RequiredArgsConstructor
public class DiscountAdmSeekResource implements DiscountAdmSeekFacade {
    private final DiscountAdmSeek discountAdmSeek;

    @Override
    @PostMapping("/find/query")
    public QueryResponse<DiscountRdo> findDiscount(@RequestBody FindDiscountAdmQuery query) {
        query.validate();
        var discount = discountAdmSeek.findDiscount(query.getDiscountId());
        return new QueryResponse<>(DiscountRdo.fromDomain(discount));
    }

    @Override
    @PostMapping("/find-multiple/query")
    public QueryResponse<List<DiscountRdo>> findDiscounts(@RequestBody FindDiscountsAdmQuery query) {
        query.validate();
        List<DiscountRdo> discountRdos = List.of();

        if (query.getDiscountIds() != null) {
            var discounts = discountAdmSeek.findDiscounts(query.getDiscountIds());
            discountRdos = discounts.stream()
                    .map(DiscountRdo::fromDomain)
                    .collect(Collectors.toList());
        } else if (query.getOffset() != null) {
            var discounts = discountAdmSeek.findDiscounts(query.getOffset());
            discountRdos = discounts.stream()
                    .map(DiscountRdo::fromDomain)
                    .collect(Collectors.toList());
        } else if (query.getActive() != null) {
            var discounts = discountAdmSeek.findActiveDiscounts();
            discountRdos = discounts.stream()
                    .map(DiscountRdo::fromDomain)
                    .collect(Collectors.toList());
        } else if (query.getScope() != null) {
            var discounts = discountAdmSeek.findByScope(query.getScope());
            discountRdos = discounts.stream()
                    .map(DiscountRdo::fromDomain)
                    .collect(Collectors.toList());
        } else if (query.getTargetId() != null) {
            var discounts = discountAdmSeek.findByTargetId(query.getTargetId());
            discountRdos = discounts.stream()
                    .map(DiscountRdo::fromDomain)
                    .collect(Collectors.toList());
        }

        return new QueryResponse<>(discountRdos);
    }
    
    @Override
    @PostMapping("/find-expired/query")
    public QueryResponse<List<DiscountRdo>> findExpiredDiscounts(FindExpiredDiscountsAdmQuery query) {
        var discounts = discountAdmSeek.findExpiredDiscounts();
        List<DiscountRdo> discountRdos = discounts.stream()
            .map(DiscountRdo::fromDomain)
            .collect(Collectors.toList());
        return new QueryResponse<>(discountRdos);
    }

    @Override
    @PostMapping("/find-valid/query")
    public QueryResponse<List<DiscountRdo>> findValidDiscounts(FindValidDiscountsAdmQuery query) {
        var discounts = discountAdmSeek.findValidDiscounts();
        List<DiscountRdo> discountRdos = discounts.stream()
            .map(DiscountRdo::fromDomain)
            .collect(Collectors.toList());
        return new QueryResponse<>(discountRdos);
    }

    @Override
    @PostMapping("/find-applicable/query")
    public QueryResponse<List<DiscountRdo>> findApplicableDiscounts(FindApplicableDiscountsAdmQuery query) {
        query.validate();
        var discounts = discountAdmSeek.findApplicableDiscounts(query.getTargetId(), query.getScope());
        List<DiscountRdo> discountRdos = discounts.stream()
            .map(DiscountRdo::fromDomain)
            .collect(Collectors.toList());
        return new QueryResponse<>(discountRdos);
    }
} 