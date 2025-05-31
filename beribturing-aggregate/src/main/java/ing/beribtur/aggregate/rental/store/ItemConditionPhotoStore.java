package ing.beribtur.aggregate.rental.store;


import ing.beribtur.aggregate.rental.entity.ItemConditionPhoto;

import java.util.List;

public interface ItemConditionPhotoStore {
    //
    void create(ItemConditionPhoto itemConditionPhoto);
    ItemConditionPhoto retrieve(String id);
    List<ItemConditionPhoto> retrieveAll(List<String> ids);
    void update(ItemConditionPhoto itemConditionPhoto);
    void delete(String id);
}
