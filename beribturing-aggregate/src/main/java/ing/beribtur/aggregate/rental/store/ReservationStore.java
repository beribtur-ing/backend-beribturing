package ing.beribtur.aggregate.rental.store;


import ing.beribtur.aggregate.rental.entity.Reservation;

import java.util.List;

public interface ReservationStore {
    //
    void create(Reservation reservation);
    Reservation retrieve(String id);
    List<Reservation> retrieveAll(List<String> ids);
    void update(Reservation reservation);
    void delete(String id);
}
