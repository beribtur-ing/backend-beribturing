package ing.beribtur.aggregate.rental.logic;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.util.Entities;
import ing.beribtur.aggregate.rental.entity.Reservation;
import ing.beribtur.aggregate.rental.entity.sdo.ReservationCdo;
import ing.beribtur.aggregate.rental.entity.vo.ReservationStatus;
import ing.beribtur.aggregate.rental.store.ReservationStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationLogic {
    //
    private final ReservationStore reservationStore;

    public String registerReservation(ReservationCdo reservationCdo) {
        //
        Reservation reservation = new Reservation(reservationCdo);
        if (reservationStore.exists(reservation.getId())) {
            throw new IllegalArgumentException("reservation already exists. " + reservation.getId());
        }
        reservationStore.create(reservation);
        return reservation.getId();
    }

    public List<String> registerReservations(List<ReservationCdo> reservationCdos) {
        //
        return reservationCdos.stream().map(this::registerReservation).collect(Collectors.toList());
    }

    public Reservation findReservation(String reservationId) {
        //
        Reservation reservation = reservationStore.retrieve(reservationId);
        if (reservation == null) {
            throw new NoSuchElementException("Reservation id: " + reservationId);
        }
        return reservation;
    }

    public List<Reservation> findReservations(Offset offset) {
        //
        return reservationStore.retrieveList(offset);
    }

    public void modifyReservation(String reservationId, NameValueList nameValueList) {
        //
        Reservation reservation = findReservation(reservationId);
        reservation.modify(nameValueList);
        reservationStore.update(reservation);
    }

    public void modifyReservation(Reservation reservation) {
        //
        Reservation oldReservation = findReservation(reservation.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldReservation, reservation);
        if (!nameValues.list().isEmpty()) {
            modifyReservation(reservation.getId(), nameValues);
        }
    }

    public void removeReservation(String reservationId) {
        //
        Reservation reservation = findReservation(reservationId);
        reservationStore.delete(reservation);
    }

    public boolean existsReservation(String reservationId) {
        //
        return reservationStore.exists(reservationId);
    }

    public List<Reservation> findByRequesterId(String requesterId) {
        //
        return reservationStore.retrieveByRequesterId(requesterId);
    }

    public List<Reservation> findByProductVariantId(String productVariantId) {
        //
        return reservationStore.retrieveByProductVariantId(productVariantId);
    }

    public List<Reservation> findByStatus(ReservationStatus status) {
        //
        return reservationStore.retrieveByStatus(status.name());
    }
}
