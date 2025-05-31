package ing.beribtur.aggregate.payment.store;


import ing.beribtur.aggregate.payment.entity.Discount;

import java.util.List;

public interface DiscountStore {
    //
    void create(Discount discount);
    Discount retrieve(String id);
    List<Discount> retrieveAll(List<String> ids);
    void update(Discount discount);
    void delete(String id);
}
