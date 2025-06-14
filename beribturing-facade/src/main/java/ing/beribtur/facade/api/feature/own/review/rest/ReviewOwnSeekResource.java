package ing.beribtur.facade.api.feature.own.review.rest;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.review.entity.Review;
import ing.beribtur.facade.api.feature.own.review.query.FindReviewOwnQuery;
import ing.beribtur.facade.api.feature.own.review.query.FindReviewsByProductVariantIdOwnQuery;
import ing.beribtur.facade.api.feature.own.review.query.FindReviewsByRecordOwnQuery;
import ing.beribtur.feature.own.review.seek.ReviewOwnSeek;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    private final ReviewOwnSeek reviewOwnSeek;

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

    @Override
    @PostMapping("/find-reviews-by-product-variant-id/query")
    public QueryResponse<List<Review>> findReviewsByVariantId(FindReviewsByProductVariantIdOwnQuery query) {
        //
        query.validate();
        String productVariantId = query.getProductVariantId();
        Offset offset = query.getOffset();
        Page<Review> response = reviewOwnSeek.findReviewsByProductVariantId(productVariantId, offset);
        query.setResponse(response);
        return query.getResponse();
    }
}

