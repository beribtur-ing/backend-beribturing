package ing.beribtur.storejpa.aggregate.rental.repository;

import ing.beribtur.storejpa.aggregate.rental.jpo.ReservationJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationJpo, String> {
    // Example custom finder methods (optional)
    List<ReservationJpo> findByProductVariantId(String productVariantId);
    List<ReservationJpo> findByRequesterId(String requesterId);
    List<ReservationJpo> findByStatus(String status);
    List<ReservationJpo> findAllByOwnerIdAndStatusIsOrStatusIsNull(String ownerId, String status);
    List<ReservationJpo> findByProductVariantIdAndStatus(String productVariantId, String status);
}
