package ing.beribtur.facade.api.feature.rnt.review.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.review.entity.Review;
import ing.beribtur.facade.api.feature.rnt.review.query.FindReviewRntQuery;
import ing.beribtur.facade.api.feature.rnt.review.query.FindReviewsByRecordRntQuery;
import ing.beribtur.feature.rnt.review.seek.ReviewRntSeek;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/renter/review")
public class ReviewRntSeekResource implements ReviewRntSeekFacade {
    //
    private final ReviewRntSeek reviewRntSeek;

    @Override
    @PostMapping("/find-review/query")
    public QueryResponse<Review> findReview(@RequestBody FindReviewRntQuery query) {
        //
        query.validate();
        String reviewId = query.getReviewId();
        Review response = reviewRntSeek.findReviewById(reviewId);
        return new QueryResponse<>(response);
    }

    @Override
    @PostMapping("/find-reviews-by-record/query")
    public QueryResponse<List<Review>> findReviewsByRecord(@RequestBody FindReviewsByRecordRntQuery query) {
        //
        query.validate();
        String recordId = query.getRecordId();
        List<Review> response = reviewRntSeek.findReviewsByRecordId(recordId);
        return new QueryResponse<>(response);
    }
}

