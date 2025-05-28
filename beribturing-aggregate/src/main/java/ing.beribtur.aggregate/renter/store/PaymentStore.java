package ing.beribtur.aggregate.renter.store;


import ing.beribtur.aggregate.renter.entity.Payment;

import java.util.List;

public interface PaymentStore {
    //
    Payment create(Payment payment);
    Payment retrieve(Long id);
    List<Payment> retrieveAll(List<Long> ids);
    Payment update(Payment payment);
    void delete(Long id);
}
