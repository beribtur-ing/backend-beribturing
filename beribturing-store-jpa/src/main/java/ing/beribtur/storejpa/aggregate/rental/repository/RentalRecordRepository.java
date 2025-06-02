package ing.beribtur.storejpa.aggregate.rental.repository;


import ing.beribtur.storejpa.aggregate.rental.jpo.RentalRecordJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRecordRepository extends JpaRepository<RentalRecordJpo, String> {
    //
    List<RentalRecordJpo> findByProductVariantId(String productVariantId);
    List<RentalRecordJpo> findByLendeeId(String lendeeId);
    List<RentalRecordJpo> findByStatus(String status);
    List<RentalRecordJpo> findByStatusAndLendeeId(String status, String lendeeId);
}

