package ing.beribtur.aggregate.rental.store;


import ing.beribtur.aggregate.rental.entity.ItemConditionCheck;

import java.util.List;

public interface ItemConditionCheckStore {
    //
    ItemConditionCheck create(ItemConditionCheck itemConditionCheck);
    ItemConditionCheck retrieve(String id);
    List<ItemConditionCheck> retrieveAll(List<String> ids);
    ItemConditionCheck update(ItemConditionCheck itemConditionCheck);
    void delete(String id);
}
