package ing.beribtur.feature.shared.customstore;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.review.entity.Review;
import org.springframework.data.domain.Page;

public interface ReviewCustomStore {
    //
    Page<Review> findReviewsByProductVariantId(String productVariantId, Offset offset);
}
