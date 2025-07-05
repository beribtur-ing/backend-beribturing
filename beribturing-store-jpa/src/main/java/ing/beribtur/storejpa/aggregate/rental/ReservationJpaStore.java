package ing.beribtur.storejpa.aggregate.rental;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.rental.entity.Reservation;
import ing.beribtur.aggregate.rental.entity.vo.ReservationStatus;
import ing.beribtur.aggregate.rental.store.ReservationStore;
import ing.beribtur.storejpa.aggregate.rental.jpo.ReservationJpo;
import ing.beribtur.storejpa.aggregate.rental.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ReservationJpaStore implements ReservationStore {

    private final ReservationRepository reservationRepository;

    private Pageable createPageable(Offset offset) {
        /* Gen by Vizend Vista v7.0.0 */
        if (offset.getSortDirection() != null && offset.getSortingField() != null) {
            return PageRequest.of(offset.page(), offset.limit(), (offset.ascendingSort() ? Sort.Direction.ASC : Sort.Direction.DESC), offset.getSortingField());
        } else {
            return PageRequest.of(offset.page(), offset.limit());
        }
    }

    @Override
    public void create(Reservation reservation) {
        ReservationJpo reservationJpo = new ReservationJpo(reservation);
        reservationRepository.save(reservationJpo);
        reservation.setId(reservationJpo.getId());
    }

    @Override
    public void createAll(List<Reservation> reservations) {
        List<ReservationJpo> jpos = reservations.stream()
                .map(ReservationJpo::new)
                .collect(Collectors.toList());

        List<ReservationJpo> savedJpos = reservationRepository.saveAll(jpos);

        // Update the IDs in the domain entities
        for (int i = 0; i < reservations.size(); i++) {
            reservations.get(i).setId(savedJpos.get(i).getId());
        }
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
    public List<Reservation> retrieveList(Offset offset) {
        PageRequest pageRequest = PageRequest.of(offset.page(), offset.limit());
        return reservationRepository.findAll(pageRequest).stream()
                .map(ReservationJpo::toDomain)
                .collect(Collectors.toList());
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
    public void delete(Reservation reservation) {
        delete(reservation.getId());
    }

    @Override
    public void delete(String id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        return reservationRepository.existsById(id);
    }

    @Override
    public List<Reservation> retrieveByRequesterId(String requesterId) {
        return ReservationJpo.toDomains(reservationRepository.findByRequesterId(requesterId));
    }

    @Override
    public List<Reservation> retrieveByProductVariantId(String productVariantId) {
        return ReservationJpo.toDomains(reservationRepository.findByProductVariantId(productVariantId));
    }

    @Override
    public List<Reservation> retrieveByStatus(String status) {
        return ReservationJpo.toDomains(reservationRepository.findByStatus(status));
    }

    @Override
    public List<Reservation> retrieveAllByOwnerId(String ownerId, String status, Offset offset) {
        Pageable pageable = createPageable(offset);
        return ReservationJpo.toDomains(reservationRepository.findAllByOwnerIdAndStatusIsOrStatusIsNull(ownerId, status,  pageable));
    }

    // Additional methods for specific queries
    public List<Reservation> findByProductVariantIdAndStatus(String productVariantId, String status) {
        return ReservationJpo.toDomains(reservationRepository.findByProductVariantIdAndStatus(productVariantId, status));
    }

    @Override
    public int countOfActiveBookingsByOwnerId(String ownerId) {
        //
        return this.reservationRepository.countByOwnerIdAndStatus(ownerId, ReservationStatus.Approved.name());
    }

    public long countByStatus(String status) {
        //
        return reservationRepository.countByStatus(status);
    }
}
