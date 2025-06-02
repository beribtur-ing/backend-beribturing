package ing.beribtur.storejpa.aggregate.payment.repository;

import ing.beribtur.storejpa.aggregate.payment.jpo.RentalDepositJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalDepositRepository extends JpaRepository<RentalDepositJpo, String> {
    List<RentalDepositJpo> findByRentalRecordId(String rentalRecordId);
    List<RentalDepositJpo> findByPayerId(String payerId);
    List<RentalDepositJpo> findByStatus(String status);
    List<RentalDepositJpo> findByRentalRecordIdAndStatus(String rentalRecordId, String status);
}