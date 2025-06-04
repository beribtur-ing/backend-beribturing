package ing.beribtur.aggregate.rental.logic;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.util.Entities;
import ing.beribtur.aggregate.rental.entity.ItemConditionCheck;
import ing.beribtur.aggregate.rental.entity.sdo.ItemConditionCheckCdo;
import ing.beribtur.aggregate.rental.entity.vo.ConditionCheckType;
import ing.beribtur.aggregate.rental.store.ItemConditionCheckStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemConditionCheckLogic {
    //
    private final ItemConditionCheckStore itemConditionCheckStore;

    public String registerItemConditionCheck(ItemConditionCheckCdo itemConditionCheckCdo) {
        //
        ItemConditionCheck itemConditionCheck = new ItemConditionCheck(itemConditionCheckCdo);
        if (itemConditionCheckStore.exists(itemConditionCheck.getId())) {
            throw new IllegalArgumentException("item condition check already exists. " + itemConditionCheck.getId());
        }
        itemConditionCheckStore.create(itemConditionCheck);
        return itemConditionCheck.getId();
    }

    public List<String> registerItemConditionChecks(List<ItemConditionCheckCdo> itemConditionCheckCdos) {
        //
        return itemConditionCheckCdos.stream().map(this::registerItemConditionCheck).collect(Collectors.toList());
    }

    public ItemConditionCheck findItemConditionCheck(String itemConditionCheckId) {
        //
        ItemConditionCheck itemConditionCheck = itemConditionCheckStore.retrieve(itemConditionCheckId);
        if (itemConditionCheck == null) {
            throw new NoSuchElementException("ItemConditionCheck id: " + itemConditionCheckId);
        }
        return itemConditionCheck;
    }

    public List<ItemConditionCheck> findItemConditionChecks(Offset offset) {
        //
        return itemConditionCheckStore.retrieveList(offset);
    }

    public void modifyItemConditionCheck(String itemConditionCheckId, NameValueList nameValueList) {
        //
        ItemConditionCheck itemConditionCheck = findItemConditionCheck(itemConditionCheckId);
        itemConditionCheck.modify(nameValueList);
        itemConditionCheckStore.update(itemConditionCheck);
    }

    public void modifyItemConditionCheck(ItemConditionCheck itemConditionCheck) {
        //
        ItemConditionCheck oldItemConditionCheck = findItemConditionCheck(itemConditionCheck.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldItemConditionCheck, itemConditionCheck);
        if (!nameValues.list().isEmpty()) {
            modifyItemConditionCheck(itemConditionCheck.getId(), nameValues);
        }
    }

    public void removeItemConditionCheck(String itemConditionCheckId) {
        //
        ItemConditionCheck itemConditionCheck = findItemConditionCheck(itemConditionCheckId);
        itemConditionCheckStore.delete(itemConditionCheck);
    }

    public boolean existsItemConditionCheck(String itemConditionCheckId) {
        //
        return itemConditionCheckStore.exists(itemConditionCheckId);
    }

    public List<ItemConditionCheck> findByRentalRecordId(String rentalRecordId) {
        //
        return itemConditionCheckStore.retrieveByRentalRecordId(rentalRecordId);
    }

    public List<ItemConditionCheck> findByVariantId(String variantId) {
        //
        return itemConditionCheckStore.retrieveByVariantId(variantId);
    }

    public List<ItemConditionCheck> findByCheckedBy(String checkedBy) {
        //
        return itemConditionCheckStore.retrieveByCheckedBy(checkedBy);
    }

    public List<ItemConditionCheck> findByCheckType(ConditionCheckType checkType) {
        //
        return itemConditionCheckStore.retrieveByCheckType(checkType);
    }
}
