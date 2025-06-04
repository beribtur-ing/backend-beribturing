package ing.beribtur.facade.api.feature.rnt.rental.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class FindItemConditionPhotoRntQuery {
    //
    private String itemConditionPhotoId;

    public void validate() {
        //
        Assert.hasText(itemConditionPhotoId, "'itemConditionPhotoId' is required.");
    }
}