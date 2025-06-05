package ing.beribtur.facade.api.feature.adm.review.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.review.entity.Review;
import ing.beribtur.facade.api.feature.adm.review.query.FindReviewAdmQuery;
import ing.beribtur.facade.api.feature.adm.review.query.FindReviewsByRecordAdmQuery;
import ing.beribtur.feature.adm.review.seek.ReviewAdmSeek;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/admin/review")
public class ReviewAdmSeekResource implements ReviewAdmSeekFacade {
    //
    private final ReviewAdmSeek reviewAdmSeek;

    @Override
    @PostMapping("/find-review/query")
    public QueryResponse<Review> findReview(@RequestBody FindReviewAdmQuery query) {
        //
        query.validate();
        String reviewId = query.getReviewId();
        Review response = reviewAdmSeek.findReviewById(reviewId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-reviews-by-record/query")
    public QueryResponse<List<Review>> findReviewsByRecord(@RequestBody FindReviewsByRecordAdmQuery query) {
        //
        query.validate();
        String recordId = query.getRecordId();
        List<Review> response = reviewAdmSeek.findReviewsByRecordId(recordId);
        return new QueryResponse<>(response);
    }
}

