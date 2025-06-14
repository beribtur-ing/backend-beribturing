package ing.beribtur.aggregate.rental.store;


import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.rental.entity.Reservation;

import java.util.List;

public interface ReservationStore {
    //
    void create(Reservation reservation);
    void createAll(List<Reservation> reservations);
    Reservation retrieve(String id);
    List<Reservation> retrieveAll(List<String> ids);
    List<Reservation> retrieveList(Offset offset);
    void update(Reservation reservation);
    void delete(Reservation reservation);
    void delete(String id);
    boolean exists(String id);

    List<Reservation> retrieveByRequesterId(String requesterId);
    List<Reservation> retrieveByProductVariantId(String productVariantId);
    List<Reservation> retrieveByStatus(String status);
    List<Reservation> retrieveAllByOwnerId(String ownerId, String reservationStatus);
}
