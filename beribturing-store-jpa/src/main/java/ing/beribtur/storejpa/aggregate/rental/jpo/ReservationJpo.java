package ing.beribtur.storejpa.aggregate.rental.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.rental.entity.Reservation;
import ing.beribtur.aggregate.rental.entity.vo.Period;
import ing.beribtur.aggregate.rental.entity.vo.ReservationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "RESERVATION")
public class ReservationJpo extends DomainEntityJpo {

    @Column(nullable = false)
    private String productVariantId;

    @Column(nullable = false)
    private String requesterId;

    @Column(nullable = false)
    private String ownerId;

    // Period fields
    @Column(nullable = false)
    private LocalDateTime startDateTime;

    @Column(nullable = false)
    private LocalDateTime endDateTime;

    @Column(nullable = false)
    private String status;

    @Column(length = 1000)
    private String note;

    public ReservationJpo(Reservation reservation) {
        //
        super(reservation);
        BeanUtils.copyProperties(reservation, this);

        // Period mapping
        if (reservation.getPeriod() != null) {
            this.startDateTime = reservation.getPeriod().getStartDateTime();
            this.endDateTime = reservation.getPeriod().getEndDateTime();
        }

        // Enum to String
        if (reservation.getStatus() != null) {
            this.status = reservation.getStatus().name();
        }
    }

    public Reservation toDomain() {
        //
        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(this, reservation);

        // Rebuild Period object
        if (this.startDateTime != null && this.endDateTime != null) {
            reservation.setPeriod(new Period(this.startDateTime, this.endDateTime));
        }

        // String to Enum
        if (this.status != null) {
            reservation.setStatus(ReservationStatus.valueOf(this.status));
        }

        return reservation;
    }

    public static List<Reservation> toDomains(List<ReservationJpo> jpos) {
        //
        return jpos.stream().map(ReservationJpo::toDomain).toList();
    }
}
