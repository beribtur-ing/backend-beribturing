package ing.beribtur.facade.api.feature.own.rental.query;

import ing.beribtur.accent.message.QueryRequest;
import ing.beribtur.aggregate.rental.entity.ItemConditionCheck;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class FindItemConditionCheckOwnQuery extends QueryRequest<ItemConditionCheck> {
    //
    private String itemConditionCheckId;

    public void validate() {
        //
        Assert.hasText(itemConditionCheckId, "'itemConditionCheckId' is required.");
    }
}