package ing.beribtur.aggregate.payment.logic;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.util.Entities;
import ing.beribtur.aggregate.payment.entity.Discount;
import ing.beribtur.aggregate.payment.entity.sdo.DiscountCdo;
import ing.beribtur.aggregate.payment.entity.vo.DiscountScope;
import ing.beribtur.aggregate.payment.store.DiscountStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DiscountLogic {
    //
    private final DiscountStore discountStore;

    public String registerDiscount(DiscountCdo discountCdo) {
        //
        Discount discount = new Discount(discountCdo);
        if (discountStore.exists(discount.getId())) {
            throw new IllegalArgumentException("Discount already exists. " + discount.getId());
        }
        discountStore.create(discount);
        return discount.getId();
    }

    public List<String> registerDiscounts(List<DiscountCdo> discountCdos) {
        //
        return discountCdos.stream().map(this::registerDiscount).collect(Collectors.toList());
    }

    public Discount findDiscount(String discountId) {
        //
        Discount discount = discountStore.retrieve(discountId);
        if (discount == null) {
            throw new NoSuchElementException("Discount id: " + discountId);
        }
        return discount;
    }

    public List<Discount> findDiscounts(List<String> discountIds) {
        //
        return discountStore.retrieveAll(discountIds);
    }

    public List<Discount> findDiscounts(Offset offset) {
        //
        return discountStore.retrieveList(offset);
    }

    public void modifyDiscount(String discountId, NameValueList nameValues) {
        //
        Discount discount = findDiscount(discountId);
        discount.modify(nameValues);
        discountStore.update(discount);
    }

    public void modifyDiscount(Discount discount) {
        //
        Discount oldDiscount = findDiscount(discount.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldDiscount, discount);
        if (!nameValues.list().isEmpty()) {
            modifyDiscount(discount.getId(), nameValues);
        }
    }

    public void removeDiscount(String discountId) {
        //
        Discount discount = findDiscount(discountId);
        discountStore.delete(discount.getId());
    }

    public boolean existsDiscount(String discountId) {
        //
        return discountStore.exists(discountId);
    }

    public List<Discount> findActiveDiscounts() {
        //
        return discountStore.retrieveByActive(true);
    }

    public List<Discount> findByScope(DiscountScope scope) {
        //
        return discountStore.retrieveByScope(scope);
    }

    public List<Discount> findByTargetId(String targetId) {
        //
        return discountStore.retrieveByTargetId(targetId);
    }

    public void activateDiscount(String discountId) {
        //
        Discount discount = findDiscount(discountId);
        discount.setActive(true);
        discountStore.update(discount);
    }

    public void deactivateDiscount(String discountId) {
        //
        Discount discount = findDiscount(discountId);
        discount.setActive(false);
        discountStore.update(discount);
    }

    public List<Discount> findExpiredDiscounts() {
        //
        LocalDateTime now = LocalDateTime.now();
        return discountStore.retrieveByEndDateBefore(now);
    }

    public List<Discount> findValidDiscounts() {
        //
        LocalDateTime now = LocalDateTime.now();
        return discountStore.retrieveValidDiscounts(now);
    }

    public List<Discount> findApplicableDiscounts(String targetId, DiscountScope scope) {
        //
        LocalDateTime now = LocalDateTime.now();
        return discountStore.retrieveApplicableDiscounts(targetId, scope, now);
    }
}
