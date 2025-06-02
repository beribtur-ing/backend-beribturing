package ing.beribtur.storejpa.aggregate.rental.repository;

import ing.beribtur.storejpa.aggregate.rental.jpo.ItemConditionCheckJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemConditionCheckRepository extends JpaRepository<ItemConditionCheckJpo, String> {
    List<ItemConditionCheckJpo> findByRentalRecordId(String rentalRecordId);
    List<ItemConditionCheckJpo> findByVariantId(String variantId);
    List<ItemConditionCheckJpo> findByCheckedBy(String checkedBy);
    List<ItemConditionCheckJpo> findByCheckType(String checkType);
    List<ItemConditionCheckJpo> findByRentalRecordIdAndCheckType(String rentalRecordId, String checkType);
    List<ItemConditionCheckJpo> findByVariantIdAndCheckType(String variantId, String checkType);
}