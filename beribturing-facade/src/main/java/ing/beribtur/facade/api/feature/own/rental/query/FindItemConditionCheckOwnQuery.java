package ing.beribtur.facade.api.feature.own.rental.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class FindItemConditionCheckOwnQuery {
    //
    private String itemConditionCheckId;

    public void validate() {
        //
        Assert.hasText(itemConditionCheckId, "'itemConditionCheckId' is required.");
    }
}