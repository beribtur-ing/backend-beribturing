package ing.beribtur.feature.own.review.flow;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.review.entity.sdo.ReviewCdo;
import ing.beribtur.aggregate.review.logic.ReviewLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewOwnFlow {
    //
    private final ReviewLogic reviewLogic;

    public String registerReview(ReviewCdo reviewCdo) {
        //
        return reviewLogic.registerReview(reviewCdo);
    }

    public String modifyReview(String reviewId, NameValueList nameValueList) {
        //
        reviewLogic.modifyReview(reviewId, nameValueList);
        return reviewId;
    }

    public String removeReview(String reviewId) {
        //
        reviewLogic.removeReview(reviewId);
        return reviewId;
    }
}

