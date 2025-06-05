package ing.beribtur.feature.adm.review.flow;

import ing.beribtur.aggregate.review.logic.ReviewLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewAdmFlow {
    //
    private final ReviewLogic reviewLogic;

    public String hideReview(String reviewId) {
        //
        reviewLogic.hideReview(reviewId);
        return reviewId;
    }
}

