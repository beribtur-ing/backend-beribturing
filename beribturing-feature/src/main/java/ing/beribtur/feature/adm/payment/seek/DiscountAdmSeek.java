package ing.beribtur.feature.adm.payment.seek;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.payment.entity.Discount;
import ing.beribtur.aggregate.payment.entity.vo.DiscountScope;
import ing.beribtur.aggregate.payment.logic.DiscountLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DiscountAdmSeek {
    private final DiscountLogic discountLogic;

    public List<Discount> findExpiredDiscounts() {
        return discountLogic.findExpiredDiscounts();
    }

    public List<Discount> findValidDiscounts() {
        return discountLogic.findValidDiscounts();
    }

    public List<Discount> findApplicableDiscounts(String targetId, DiscountScope scope) {
        return discountLogic.findApplicableDiscounts(targetId, scope);
    }

    public Discount findDiscount(String discountId) {
        return discountLogic.findDiscount(discountId);
    }

    public List<Discount> findDiscounts(List<String> discountIds) {
        return discountLogic.findDiscounts(discountIds);
    }

    public List<Discount> findDiscounts(Offset offset) {
        return discountLogic.findDiscounts(offset);
    }

    public boolean existsDiscount(String discountId) {
        return discountLogic.existsDiscount(discountId);
    }

    public List<Discount> findActiveDiscounts() {
        return discountLogic.findActiveDiscounts();
    }

    public List<Discount> findByScope(DiscountScope scope) {
        return discountLogic.findByScope(scope);
    }

    public List<Discount> findByTargetId(String targetId) {
        return discountLogic.findByTargetId(targetId);
    }
}
