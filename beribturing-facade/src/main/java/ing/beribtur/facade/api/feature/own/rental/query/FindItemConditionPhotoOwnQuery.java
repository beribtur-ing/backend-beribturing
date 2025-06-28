package ing.beribtur.facade.api.feature.own.rental.query;

import ing.beribtur.accent.message.QueryRequest;
import ing.beribtur.aggregate.rental.entity.ItemConditionPhoto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class FindItemConditionPhotoOwnQuery extends QueryRequest<ItemConditionPhoto> {
    //
    private String itemConditionPhotoId;

    public void validate() {
        //
        Assert.hasText(itemConditionPhotoId, "'itemConditionPhotoId' is required.");
    }
}