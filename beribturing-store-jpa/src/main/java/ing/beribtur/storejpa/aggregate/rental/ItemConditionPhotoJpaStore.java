package ing.beribtur.storejpa.aggregate.rental;

import ing.beribtur.aggregate.rental.entity.ItemConditionPhoto;
import ing.beribtur.aggregate.rental.store.ItemConditionPhotoStore;
import ing.beribtur.storejpa.aggregate.rental.jpo.ItemConditionPhotoJpo;
import ing.beribtur.storejpa.aggregate.rental.repository.ItemConditionPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ItemConditionPhotoJpaStore implements ItemConditionPhotoStore {
    //
    private final ItemConditionPhotoRepository itemConditionPhotoRepository;

    @Override
    public void create(ItemConditionPhoto itemConditionPhoto) {
        ItemConditionPhotoJpo itemConditionPhotoJpo = new ItemConditionPhotoJpo(itemConditionPhoto);

        itemConditionPhotoRepository.save(itemConditionPhotoJpo);
        itemConditionPhoto.setId(itemConditionPhotoJpo.getId());
    }

    @Override
    public ItemConditionPhoto retrieve(String id) {
        ItemConditionPhotoJpo itemConditionPhotoJpo = itemConditionPhotoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ItemConditionPhoto not found: " + id));
        return itemConditionPhotoJpo.toDomain();
    }

    @Override
    public List<ItemConditionPhoto> retrieveAll(List<String> ids) {
        List<ItemConditionPhotoJpo> itemConditionPhotoJpos = itemConditionPhotoRepository.findAllById(ids);
        return itemConditionPhotoJpos.stream()
                .map(ItemConditionPhotoJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void update(ItemConditionPhoto itemConditionPhoto) {
        ItemConditionPhotoJpo itemConditionPhotoJpo = itemConditionPhotoRepository.findById(itemConditionPhoto.getId())
                .orElseThrow(() -> new IllegalArgumentException("ItemConditionPhoto not found: " + itemConditionPhoto.getId()));
        
        // Update the JPO with the domain entity's values
        ItemConditionPhotoJpo updatedJpo = new ItemConditionPhotoJpo(itemConditionPhoto);
        updatedJpo.setEntityVersion(itemConditionPhotoJpo.getEntityVersion());
        updatedJpo.setRegisteredBy(itemConditionPhotoJpo.getRegisteredBy());
        updatedJpo.setRegisteredOn(itemConditionPhotoJpo.getRegisteredOn());
        
        itemConditionPhotoRepository.save(updatedJpo);
    }

    @Override
    public void delete(String id) {
        itemConditionPhotoRepository.deleteById(id);
    }
    
    // Additional methods for specific queries
    public List<ItemConditionPhoto> findByCheckId(String checkId) {
        return ItemConditionPhotoJpo.toDomains(itemConditionPhotoRepository.findByCheckId(checkId));
    }
    
    public List<ItemConditionPhoto> findByUrlContaining(String urlPattern) {
        return ItemConditionPhotoJpo.toDomains(itemConditionPhotoRepository.findByUrlContaining(urlPattern));
    }
}