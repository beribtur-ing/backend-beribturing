package ing.beribtur.feature.own.review.seek;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.review.entity.Review;
import ing.beribtur.aggregate.review.logic.ReviewLogic;
import ing.beribtur.feature.shared.customstore.ReviewCustomStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewOwnSeek {
    //
    private final ReviewLogic reviewLogic;
    private final ReviewCustomStore reviewCustomStore;

    public Review findReviewById(String reviewId) {
        //
        return reviewLogic.findReview(reviewId);
    }

    public List<Review> findReviewsByReviewerId(String reviewerId) {
        //
        return reviewLogic.findReviewsByReviewerId(reviewerId);
    }

    public List<Review> findReviewsByRecordId(String recordId) {
        //
        return reviewLogic.findReviewsByRecordId(recordId);
    }

    public List<Review> findReviewsByRating(int rating) {
        //
        return reviewLogic.findReviewsByRating(rating);
    }

    public Page<Review> findReviewsByProductVariantId(String productVariantId, Offset offset) {
        //
        return reviewCustomStore.findReviewsByProductVariantId(productVariantId, offset);
    }
}

