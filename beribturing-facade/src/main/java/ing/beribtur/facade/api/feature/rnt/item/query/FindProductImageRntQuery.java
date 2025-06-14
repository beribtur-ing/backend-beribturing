package ing.beribtur.facade.api.feature.rnt.item.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class FindProductImageRntQuery {
    //
    private String imageId;

    public void validate() {
        //
        Assert.hasText(imageId, "'imageId' is required.");
    }
}