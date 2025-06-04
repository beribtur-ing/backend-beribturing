package ing.beribtur.facade.api.feature.own.rental.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class FindItemConditionPhotoOwnQuery {
    //
    private String itemConditionPhotoId;

    public void validate() {
        //
        Assert.hasText(itemConditionPhotoId, "'itemConditionPhotoId' is required.");
    }
}