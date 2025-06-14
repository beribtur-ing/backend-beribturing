package ing.beribtur.facade.api.feature.own.item.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class FindProductImageOwnQuery {
    //
    private String imageId;

    public void validate() {
        //
        Assert.hasText(imageId, "'imageId' is required.");
    }
}