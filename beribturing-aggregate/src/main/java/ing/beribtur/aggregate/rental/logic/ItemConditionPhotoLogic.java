package ing.beribtur.aggregate.rental.logic;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.util.Entities;
import ing.beribtur.aggregate.rental.entity.ItemConditionPhoto;
import ing.beribtur.aggregate.rental.entity.sdo.ItemConditionPhotoCdo;
import ing.beribtur.aggregate.rental.store.ItemConditionPhotoStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemConditionPhotoLogic {
    //
    private final ItemConditionPhotoStore itemConditionPhotoStore;

    public String registerItemConditionPhoto(ItemConditionPhotoCdo itemConditionPhotoCdo) {
        //
        ItemConditionPhoto itemConditionPhoto = new ItemConditionPhoto(itemConditionPhotoCdo);
        if (itemConditionPhotoStore.exists(itemConditionPhoto.getId())) {
            throw new IllegalArgumentException("item condition photo already exists. " + itemConditionPhoto.getId());
        }
        itemConditionPhotoStore.create(itemConditionPhoto);
        return itemConditionPhoto.getId();
    }

    public List<String> registerItemConditionPhotos(List<ItemConditionPhotoCdo> itemConditionPhotoCdos) {
        //
        return itemConditionPhotoCdos.stream().map(this::registerItemConditionPhoto).collect(Collectors.toList());
    }

    public ItemConditionPhoto findItemConditionPhoto(String itemConditionPhotoId) {
        //
        ItemConditionPhoto itemConditionPhoto = itemConditionPhotoStore.retrieve(itemConditionPhotoId);
        if (itemConditionPhoto == null) {
            throw new NoSuchElementException("ItemConditionPhoto id: " + itemConditionPhotoId);
        }
        return itemConditionPhoto;
    }

    public List<ItemConditionPhoto> findItemConditionPhotos(Offset offset) {
        //
        return itemConditionPhotoStore.retrieveList(offset);
    }

    public void modifyItemConditionPhoto(String itemConditionPhotoId, NameValueList nameValueList) {
        //
        ItemConditionPhoto itemConditionPhoto = findItemConditionPhoto(itemConditionPhotoId);
        itemConditionPhoto.modify(nameValueList);
        itemConditionPhotoStore.update(itemConditionPhoto);
    }

    public void modifyItemConditionPhoto(ItemConditionPhoto itemConditionPhoto) {
        //
        ItemConditionPhoto oldItemConditionPhoto = findItemConditionPhoto(itemConditionPhoto.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldItemConditionPhoto, itemConditionPhoto);
        if (!nameValues.list().isEmpty()) {
            modifyItemConditionPhoto(itemConditionPhoto.getId(), nameValues);
        }
    }

    public void removeItemConditionPhoto(String itemConditionPhotoId) {
        //
        ItemConditionPhoto itemConditionPhoto = findItemConditionPhoto(itemConditionPhotoId);
        itemConditionPhotoStore.delete(itemConditionPhoto);
    }

    public boolean existsItemConditionPhoto(String itemConditionPhotoId) {
        //
        return itemConditionPhotoStore.exists(itemConditionPhotoId);
    }

    public List<ItemConditionPhoto> findByItemConditionCheckId(String itemConditionCheckId) {
        //
        return itemConditionPhotoStore.retrieveByItemConditionCheckId(itemConditionCheckId);
    }

    public List<ItemConditionPhoto> findByItemConditionCheckIdOrderBySequenceAsc(String itemConditionCheckId) {
        //
        return itemConditionPhotoStore.retrieveByItemConditionCheckIdOrderBySequenceAsc(itemConditionCheckId);
    }
}
