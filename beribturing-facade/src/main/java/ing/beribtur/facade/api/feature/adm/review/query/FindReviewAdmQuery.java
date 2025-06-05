package ing.beribtur.facade.api.feature.adm.review.query;

import ing.beribtur.accent.message.QueryRequest;
import ing.beribtur.aggregate.review.entity.Review;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class FindReviewAdmQuery extends QueryRequest<Review> {
    //
    private String reviewId;

    public void validate() {
        //
        Assert.hasText(reviewId, "'reviewId' is required.");
    }
}
