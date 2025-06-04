package ing.beribtur.aggregate.rental.store;


import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.rental.entity.ItemConditionPhoto;

import java.util.List;

public interface ItemConditionPhotoStore {
    //
    void create(ItemConditionPhoto itemConditionPhoto);
    void createAll(List<ItemConditionPhoto> itemConditionPhotos);
    ItemConditionPhoto retrieve(String id);
    List<ItemConditionPhoto> retrieveAll(List<String> ids);
    List<ItemConditionPhoto> retrieveList(Offset offset);
    void update(ItemConditionPhoto itemConditionPhoto);
    void delete(ItemConditionPhoto itemConditionPhoto);
    void delete(String id);
    boolean exists(String id);

    List<ItemConditionPhoto> retrieveByItemConditionCheckId(String itemConditionCheckId);
    List<ItemConditionPhoto> retrieveByItemConditionCheckIdOrderBySequenceAsc(String itemConditionCheckId);
}
