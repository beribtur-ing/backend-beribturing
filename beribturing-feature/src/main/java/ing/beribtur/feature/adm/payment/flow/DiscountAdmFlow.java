package ing.beribtur.feature.adm.payment.flow;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.payment.entity.Discount;
import ing.beribtur.aggregate.payment.entity.sdo.DiscountCdo;
import ing.beribtur.aggregate.payment.logic.DiscountLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DiscountAdmFlow {
    private final DiscountLogic discountLogic;

    public String registerDiscount(DiscountCdo discountCdo) {
        return discountLogic.registerDiscount(discountCdo);
    }

    public List<String> registerDiscounts(List<DiscountCdo> discountCdos) {
        return discountLogic.registerDiscounts(discountCdos);
    }

    public void modifyDiscount(String discountId, NameValueList nameValues) {
        discountLogic.modifyDiscount(discountId, nameValues);
    }

    public void modifyDiscount(Discount discount) {
        discountLogic.modifyDiscount(discount);
    }

    public void removeDiscount(String discountId) {
        discountLogic.removeDiscount(discountId);
    }

    public void activateDiscount(String discountId) {
        discountLogic.activateDiscount(discountId);
    }

    public void deactivateDiscount(String discountId) {
        discountLogic.deactivateDiscount(discountId);
    }
} 