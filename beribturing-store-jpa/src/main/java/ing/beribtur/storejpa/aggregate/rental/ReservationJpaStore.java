package ing.beribtur.storejpa.aggregate.rental;

import ing.beribtur.aggregate.rental.entity.Reservation;
import ing.beribtur.aggregate.rental.store.ReservationStore;
import ing.beribtur.storejpa.aggregate.rental.jpo.ReservationJpo;
import ing.beribtur.storejpa.aggregate.rental.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReservationJpaStore implements ReservationStore {

    private final ReservationRepository reservationRepository;

    @Override
    public void create(Reservation reservation) {
        ReservationJpo reservationJpo = new ReservationJpo(reservation);
        reservationRepository.save(reservationJpo);
        reservation.setId(reservationJpo.getId());
    }

    @Override
    public Reservation retrieve(String id) {
        ReservationJpo reservationJpo = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found: " + id));
        return reservationJpo.toDomain();
    }

    @Override
    public List<Reservation> retrieveAll(List<String> ids) {
        List<ReservationJpo> jpos = reservationRepository.findAllById(ids);
        return jpos.stream().map(ReservationJpo::toDomain).toList();
    }

    @Override
    public void update(Reservation reservation) {
        ReservationJpo existingJpo = reservationRepository.findById(reservation.getId())
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found: " + reservation.getId()));

        ReservationJpo updatedJpo = new ReservationJpo(reservation);
        updatedJpo.setEntityVersion(existingJpo.getEntityVersion());
        updatedJpo.setRegisteredBy(existingJpo.getRegisteredBy());
        updatedJpo.setRegisteredOn(existingJpo.getRegisteredOn());

        reservationRepository.save(updatedJpo);
    }

    @Override
    public void delete(String id) {
        reservationRepository.deleteById(id);
    }

    List<Reservation> findByProductVariantId(String productVariantId) {
        return ReservationJpo.toDomains(reservationRepository.findByProductVariantId(productVariantId));
    }
    List<Reservation> findByRequesterId(String requesterId) {
        return ReservationJpo.toDomains(reservationRepository.findByRequesterId(requesterId));
    }
    List<Reservation> findByStatus(String status) {
        return ReservationJpo.toDomains(reservationRepository.findByStatus(status));
    }
    List<Reservation> findByProductVariantIdAndStatus(String productVariantId, String status) {
        return ReservationJpo.toDomains(reservationRepository.findByProductVariantIdAndStatus(productVariantId, status));
    }
}
