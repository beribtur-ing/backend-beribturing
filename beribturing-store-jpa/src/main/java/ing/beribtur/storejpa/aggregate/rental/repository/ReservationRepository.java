package ing.beribtur.storejpa.aggregate.rental.repository;

import ing.beribtur.storejpa.aggregate.rental.jpo.ReservationJpo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationJpo, String> {
    // Example custom finder methods (optional)
    List<ReservationJpo> findByProductVariantId(String productVariantId);
    List<ReservationJpo> findByRequesterId(String requesterId);
    List<ReservationJpo> findByStatus(String status);
    List<ReservationJpo> findAllByOwnerIdAndStatusIsOrStatusIsNull(String ownerId, String status, Pageable pageable);
    List<ReservationJpo> findByProductVariantIdAndStatus(String productVariantId, String status);
    int countByOwnerIdAndStatus(String ownerId, String status);
    
    // Count methods for statistics
    long countByStatus(String status);
}
