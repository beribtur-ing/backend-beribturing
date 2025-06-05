package ing.beribtur.facade.api.feature.own.review.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.review.entity.Review;
import ing.beribtur.facade.api.feature.own.review.query.FindReviewOwnQuery;
import ing.beribtur.facade.api.feature.own.review.query.FindReviewsByRecordOwnQuery;
import ing.beribtur.feature.rnt.review.seek.ReviewRntSeek;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/owner/review")
public class ReviewOwnSeekResource implements ReviewOwnSeekFacade {
    //
    private final ReviewRntSeek reviewOwnSeek;

    @Override
    @PostMapping("/find-review/query")
    public QueryResponse<Review> findReview(@RequestBody FindReviewOwnQuery query) {
        //
        query.validate();
        String reviewId = query.getReviewId();
        Review response = reviewOwnSeek.findReviewById(reviewId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-reviews-by-record/query")
    public QueryResponse<List<Review>> findReviewsByRecord(@RequestBody FindReviewsByRecordOwnQuery query) {
        //
        query.validate();
        String recordId = query.getRecordId();
        List<Review> response = reviewOwnSeek.findReviewsByRecordId(recordId);
        return new QueryResponse<>(response);
    }
}

