package ing.beribtur.feature.adm.review.seek;

import ing.beribtur.aggregate.review.entity.Review;
import ing.beribtur.aggregate.review.logic.ReviewLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewAdmSeek {
    //
    private final ReviewLogic reviewLogic;

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
}

