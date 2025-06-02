package ing.beribtur.storejpa.aggregate.rental;

import ing.beribtur.aggregate.rental.entity.ItemConditionCheck;
import ing.beribtur.aggregate.rental.entity.vo.ConditionCheckType;
import ing.beribtur.aggregate.rental.store.ItemConditionCheckStore;
import ing.beribtur.storejpa.aggregate.rental.jpo.ItemConditionCheckJpo;
import ing.beribtur.storejpa.aggregate.rental.repository.ItemConditionCheckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ItemConditionCheckJpaStore implements ItemConditionCheckStore {
    //
    private final ItemConditionCheckRepository itemConditionCheckRepository;

    @Override
    public void create(ItemConditionCheck itemConditionCheck) {
        ItemConditionCheckJpo itemConditionCheckJpo = new ItemConditionCheckJpo(itemConditionCheck);

        itemConditionCheckRepository.save(itemConditionCheckJpo);
        itemConditionCheck.setId(itemConditionCheckJpo.getId());
    }

    @Override
    public ItemConditionCheck retrieve(String id) {
        ItemConditionCheckJpo itemConditionCheckJpo = itemConditionCheckRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ItemConditionCheck not found: " + id));
        return itemConditionCheckJpo.toDomain();
    }

    @Override
    public List<ItemConditionCheck> retrieveAll(List<String> ids) {
        List<ItemConditionCheckJpo> itemConditionCheckJpos = itemConditionCheckRepository.findAllById(ids);
        return itemConditionCheckJpos.stream()
                .map(ItemConditionCheckJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void update(ItemConditionCheck itemConditionCheck) {
        ItemConditionCheckJpo itemConditionCheckJpo = itemConditionCheckRepository.findById(itemConditionCheck.getId())
                .orElseThrow(() -> new IllegalArgumentException("ItemConditionCheck not found: " + itemConditionCheck.getId()));
        
        // Update the JPO with the domain entity's values
        ItemConditionCheckJpo updatedJpo = new ItemConditionCheckJpo(itemConditionCheck);
        updatedJpo.setEntityVersion(itemConditionCheckJpo.getEntityVersion());
        updatedJpo.setRegisteredBy(itemConditionCheckJpo.getRegisteredBy());
        updatedJpo.setRegisteredOn(itemConditionCheckJpo.getRegisteredOn());
        
        itemConditionCheckRepository.save(updatedJpo);
    }

    @Override
    public void delete(String id) {
        itemConditionCheckRepository.deleteById(id);
    }
    
    // Additional methods for specific queries
    public List<ItemConditionCheck> findByRentalRecordId(String rentalRecordId) {
        return ItemConditionCheckJpo.toDomains(itemConditionCheckRepository.findByRentalRecordId(rentalRecordId));
    }
    
    public List<ItemConditionCheck> findByVariantId(String variantId) {
        return ItemConditionCheckJpo.toDomains(itemConditionCheckRepository.findByVariantId(variantId));
    }
    
    public List<ItemConditionCheck> findByCheckedBy(String checkedBy) {
        return ItemConditionCheckJpo.toDomains(itemConditionCheckRepository.findByCheckedBy(checkedBy));
    }
    
    public List<ItemConditionCheck> findByCheckType(ConditionCheckType checkType) {
        return ItemConditionCheckJpo.toDomains(itemConditionCheckRepository.findByCheckType(checkType.name()));
    }
    
    public List<ItemConditionCheck> findByRentalRecordIdAndCheckType(String rentalRecordId, ConditionCheckType checkType) {
        return ItemConditionCheckJpo.toDomains(
            itemConditionCheckRepository.findByRentalRecordIdAndCheckType(rentalRecordId, checkType.name())
        );
    }
    
    public List<ItemConditionCheck> findByVariantIdAndCheckType(String variantId, ConditionCheckType checkType) {
        return ItemConditionCheckJpo.toDomains(
            itemConditionCheckRepository.findByVariantIdAndCheckType(variantId, checkType.name())
        );
    }
}