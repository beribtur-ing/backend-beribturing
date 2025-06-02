package ing.beribtur.storejpa.aggregate.rental.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.rental.entity.ItemConditionPhoto;
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
@Table(name = "ITEM_CONDITION_PHOTO")
public class ItemConditionPhotoJpo extends DomainEntityJpo {

    @Column(nullable = false)
    private String checkId;
    
    @Column(nullable = false)
    private String url;

    public ItemConditionPhotoJpo(ItemConditionPhoto itemConditionPhoto) {
        //
        super(itemConditionPhoto);
        BeanUtils.copyProperties(itemConditionPhoto, this);
    }

    public ItemConditionPhoto toDomain() {
        //
        ItemConditionPhoto itemConditionPhoto = new ItemConditionPhoto();
        BeanUtils.copyProperties(this, itemConditionPhoto);
        return itemConditionPhoto;
    }

    public static List<ItemConditionPhoto> toDomains(List<ItemConditionPhotoJpo> jpos) {
        //
        return jpos.stream().map(ItemConditionPhotoJpo::toDomain).toList();
    }
}