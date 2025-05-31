package ing.beribtur.aggregate.rental.store;


import ing.beribtur.aggregate.rental.entity.RentalRecord;

import java.util.List;

public interface RentalRecordStore {
    //
    RentalRecord create(RentalRecord rentalRecord);
    RentalRecord retrieve(String id);
    List<RentalRecord> retrieveAll(List<String> ids);
    RentalRecord update(RentalRecord rentalRecord);
    void delete(String id);
}
