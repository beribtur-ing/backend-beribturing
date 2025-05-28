package ing.beribtur.aggregate.rentalprovider.store;


import ing.beribtur.aggregate.rentalprovider.entity.RentalItem;

import java.util.List;

public interface RentalItemStore {
    //
    RentalItem create(RentalItem rentalItem);
    RentalItem retrieve(Long id);
    List<RentalItem> retrieveAll(List<Long> ids);
    RentalItem update(RentalItem rentalItem);
    void delete(Long id);
}
