package ing.beribtur.facade.api.feature.own.review.query;

import ing.beribtur.accent.message.OffsetQueryRequest;
import ing.beribtur.aggregate.review.entity.Review;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.List;

@Getter
@Setter
public class FindReviewsByProductVariantIdOwnQuery extends OffsetQueryRequest<List<Review>> {
    //
    private String productVariantId;

    public void validate() {
        //
        Assert.hasText(productVariantId, "'productVariantId' is required.");
    }
}