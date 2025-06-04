package ing.beribtur.aggregate.rental.store;


import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.rental.entity.ItemConditionCheck;
import ing.beribtur.aggregate.rental.entity.vo.ConditionCheckType;

import java.util.List;

public interface ItemConditionCheckStore {
    //
    void create(ItemConditionCheck itemConditionCheck);
    void createAll(List<ItemConditionCheck> itemConditionChecks);
    ItemConditionCheck retrieve(String id);
    List<ItemConditionCheck> retrieveAll(List<String> ids);
    List<ItemConditionCheck> retrieveList(Offset offset);
    void update(ItemConditionCheck itemConditionCheck);
    void delete(ItemConditionCheck itemConditionCheck);
    void delete(String id);
    boolean exists(String id);

    List<ItemConditionCheck> retrieveByRentalRecordId(String rentalRecordId);
    List<ItemConditionCheck> retrieveByVariantId(String variantId);
    List<ItemConditionCheck> retrieveByCheckedBy(String checkedBy);
    List<ItemConditionCheck> retrieveByCheckType(ConditionCheckType checkType);
}
