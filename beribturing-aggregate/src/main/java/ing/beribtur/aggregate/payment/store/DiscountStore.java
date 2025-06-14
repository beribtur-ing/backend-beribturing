package ing.beribtur.aggregate.payment.store;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.payment.entity.Discount;
import ing.beribtur.aggregate.payment.entity.vo.DiscountScope;

import java.time.LocalDateTime;
import java.util.List;

public interface DiscountStore {
    //
    void create(Discount discount);
    Discount retrieve(String id);
    List<Discount> retrieveAll(List<String> ids);
    List<Discount> retrieveList(Offset offset);
    void update(Discount discount);
    void delete(String id);
    boolean exists(String id);
    
    List<Discount> retrieveByActive(boolean active);
    List<Discount> retrieveByScope(DiscountScope scope);
    List<Discount> retrieveByTargetId(String targetId);
    List<Discount> retrieveByEndDateBefore(LocalDateTime dateTime);
    List<Discount> retrieveValidDiscounts(LocalDateTime now);
    List<Discount> retrieveApplicableDiscounts(String targetId, DiscountScope scope, LocalDateTime now);
}
