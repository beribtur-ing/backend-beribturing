package ing.beribtur.storejpa.aggregate.rental;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.rental.entity.ItemConditionPhoto;
import ing.beribtur.aggregate.rental.store.ItemConditionPhotoStore;
import ing.beribtur.storejpa.aggregate.rental.jpo.ItemConditionPhotoJpo;
import ing.beribtur.storejpa.aggregate.rental.repository.ItemConditionPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
    public void createAll(List<ItemConditionPhoto> itemConditionPhotos) {
        List<ItemConditionPhotoJpo> jpos = itemConditionPhotos.stream()
                .map(ItemConditionPhotoJpo::new)
                .collect(Collectors.toList());

        List<ItemConditionPhotoJpo> savedJpos = itemConditionPhotoRepository.saveAll(jpos);

        // Update the IDs in the domain entities
        for (int i = 0; i < itemConditionPhotos.size(); i++) {
            itemConditionPhotos.get(i).setId(savedJpos.get(i).getId());
        }
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
    public List<ItemConditionPhoto> retrieveList(Offset offset) {
        PageRequest pageRequest = PageRequest.of(offset.page(), offset.limit());
        return itemConditionPhotoRepository.findAll(pageRequest).stream()
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
    public void delete(ItemConditionPhoto itemConditionPhoto) {
        delete(itemConditionPhoto.getId());
    }

    @Override
    public void delete(String id) {
        itemConditionPhotoRepository.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        return itemConditionPhotoRepository.existsById(id);
    }

    @Override
    public List<ItemConditionPhoto> retrieveByItemConditionCheckId(String itemConditionCheckId) {
        return ItemConditionPhotoJpo.toDomains(itemConditionPhotoRepository.findByCheckId(itemConditionCheckId));
    }

    @Override
    public List<ItemConditionPhoto> retrieveByItemConditionCheckIdOrderBySequenceAsc(String itemConditionCheckId) {
        // Since sequence is encoded in the ID, we can sort by ID after retrieving
        List<ItemConditionPhoto> photos = retrieveByItemConditionCheckId(itemConditionCheckId);
        return photos.stream()
                .sorted((p1, p2) -> p1.getId().compareTo(p2.getId()))
                .collect(Collectors.toList());
    }
}
