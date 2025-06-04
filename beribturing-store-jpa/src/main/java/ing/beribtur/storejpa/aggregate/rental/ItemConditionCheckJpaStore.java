package ing.beribtur.storejpa.aggregate.rental;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.rental.entity.ItemConditionCheck;
import ing.beribtur.aggregate.rental.entity.vo.ConditionCheckType;
import ing.beribtur.aggregate.rental.store.ItemConditionCheckStore;
import ing.beribtur.storejpa.aggregate.rental.jpo.ItemConditionCheckJpo;
import ing.beribtur.storejpa.aggregate.rental.repository.ItemConditionCheckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
    public void createAll(List<ItemConditionCheck> itemConditionChecks) {
        List<ItemConditionCheckJpo> jpos = itemConditionChecks.stream()
                .map(ItemConditionCheckJpo::new)
                .collect(Collectors.toList());

        List<ItemConditionCheckJpo> savedJpos = itemConditionCheckRepository.saveAll(jpos);

        // Update the IDs in the domain entities
        for (int i = 0; i < itemConditionChecks.size(); i++) {
            itemConditionChecks.get(i).setId(savedJpos.get(i).getId());
        }
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
    public List<ItemConditionCheck> retrieveList(Offset offset) {
        PageRequest pageRequest = PageRequest.of(offset.page(), offset.limit());
        return itemConditionCheckRepository.findAll(pageRequest).stream()
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
    public void delete(ItemConditionCheck itemConditionCheck) {
        delete(itemConditionCheck.getId());
    }

    @Override
    public void delete(String id) {
        itemConditionCheckRepository.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        return itemConditionCheckRepository.existsById(id);
    }

    @Override
    public List<ItemConditionCheck> retrieveByRentalRecordId(String rentalRecordId) {
        return ItemConditionCheckJpo.toDomains(itemConditionCheckRepository.findByRentalRecordId(rentalRecordId));
    }
    @Override
    public List<ItemConditionCheck> retrieveByVariantId(String variantId) {
        return ItemConditionCheckJpo.toDomains(itemConditionCheckRepository.findByVariantId(variantId));
    }
    @Override
    public List<ItemConditionCheck> retrieveByCheckedBy(String checkedBy) {
        return ItemConditionCheckJpo.toDomains(itemConditionCheckRepository.findByCheckedBy(checkedBy));
    }
    @Override
    public List<ItemConditionCheck> retrieveByCheckType(ConditionCheckType checkType) {
        return ItemConditionCheckJpo.toDomains(itemConditionCheckRepository.findByCheckType(checkType.name()));
    }
    // Additional method not in the interface
    public List<ItemConditionCheck> retrieveByCheckType(String rentalRecordId, ConditionCheckType checkType) {
        return ItemConditionCheckJpo.toDomains(
            itemConditionCheckRepository.findByRentalRecordIdAndCheckType(rentalRecordId, checkType.name())
        );
    }

    // Additional method not in the interface
    public List<ItemConditionCheck> findByVariantIdAndCheckType(String variantId, ConditionCheckType checkType) {
        return ItemConditionCheckJpo.toDomains(
            itemConditionCheckRepository.findByVariantIdAndCheckType(variantId, checkType.name())
        );
    }
}
