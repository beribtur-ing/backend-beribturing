package ing.beribtur.aggregate.payment.store;


import ing.beribtur.aggregate.payment.entity.RentalDeposit;

import java.util.List;

public interface RentalDepositStore {
    //
    void create(RentalDeposit rentalDeposit);
    RentalDeposit retrieve(String id);
    List<RentalDeposit> retrieveAll(List<String> ids);
    void update(RentalDeposit rentalDeposit);
    void delete(String id);
}
