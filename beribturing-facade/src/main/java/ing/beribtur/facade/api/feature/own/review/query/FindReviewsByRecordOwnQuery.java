package ing.beribtur.facade.api.feature.own.review.query;

import ing.beribtur.accent.message.QueryRequest;
import ing.beribtur.aggregate.review.entity.Review;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.List;

@Getter
@Setter
public class FindReviewsByRecordOwnQuery extends QueryRequest<List<Review>> {
    //
    private String recordId;

    public void validate() {
        //
        Assert.hasText(recordId, "'recordId' is required.");
    }
}