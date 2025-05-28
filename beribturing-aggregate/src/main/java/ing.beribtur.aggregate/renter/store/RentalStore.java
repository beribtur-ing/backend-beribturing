package ing.beribtur.aggregate.renter.store;


import ing.beribtur.aggregate.renter.entity.Rental;

import java.util.List;

public interface RentalStore {
    //
    Rental create(Rental payment);
    Rental retrieve(Long id);
    List<Rental> retrieveAll(List<Long> ids);
    Rental update(Rental rental);
    void delete(Long id);
}
