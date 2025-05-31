package ing.beribtur.aggregate.payment.store;


import ing.beribtur.aggregate.payment.entity.Discount;

import java.util.List;

public interface DiscountStore {
    //
    Discount create(Discount discount);
    Discount retrieve(String id);
    List<Discount> retrieveAll(List<String> ids);
    Discount update(Discount discount);
    void delete(String id);
}
