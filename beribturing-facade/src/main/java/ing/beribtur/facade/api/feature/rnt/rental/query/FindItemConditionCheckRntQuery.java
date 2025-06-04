package ing.beribtur.facade.api.feature.rnt.rental.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class FindItemConditionCheckRntQuery {
    //
    private String itemConditionCheckId;

    public void validate() {
        //
        Assert.hasText(itemConditionCheckId, "'itemConditionCheckId' is required.");
    }
}