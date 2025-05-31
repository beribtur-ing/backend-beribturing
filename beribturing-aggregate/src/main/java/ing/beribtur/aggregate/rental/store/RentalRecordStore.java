package ing.beribtur.aggregate.rental.store;


import ing.beribtur.aggregate.rental.entity.RentalRecord;

import java.util.List;

public interface RentalRecordStore {
    //
    void create(RentalRecord rentalRecord);
    RentalRecord retrieve(String id);
    List<RentalRecord> retrieveAll(List<String> ids);
    void update(RentalRecord rentalRecord);
    void delete(String id);
}
