package ing.beribtur.storejpa.aggregate.rental.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.rental.entity.ItemConditionCheck;
import ing.beribtur.aggregate.rental.entity.vo.ConditionCheckType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ITEM_CONDITION_CHECK")
public class ItemConditionCheckJpo extends DomainEntityJpo {

    @Column(nullable = false)
    private String rentalRecordId;
    
    @Column(nullable = false)
    private String variantId;
    
    @Column(nullable = false)
    private String checkedBy;
    
    @Column(nullable = false)
    private String checkType;

    public ItemConditionCheckJpo(ItemConditionCheck itemConditionCheck) {
        //
        super(itemConditionCheck);
        BeanUtils.copyProperties(itemConditionCheck, this);
        
        // Handle enum conversion
        if (itemConditionCheck.getCheckType() != null) {
            this.checkType = itemConditionCheck.getCheckType().name();
        }
    }

    public ItemConditionCheck toDomain() {
        //
        ItemConditionCheck itemConditionCheck = new ItemConditionCheck();
        BeanUtils.copyProperties(this, itemConditionCheck);
        
        // Convert string back to enum
        if (this.checkType != null) {
            itemConditionCheck.setCheckType(ConditionCheckType.valueOf(this.checkType));
        }
        
        return itemConditionCheck;
    }

    public static List<ItemConditionCheck> toDomains(List<ItemConditionCheckJpo> jpos) {
        //
        return jpos.stream().map(ItemConditionCheckJpo::toDomain).toList();
    }
}