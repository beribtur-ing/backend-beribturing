package ing.beribtur.facade.api.feature.own.review.rest;


import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.review.entity.Review;
import ing.beribtur.facade.api.feature.own.review.query.FindReviewOwnQuery;
import ing.beribtur.facade.api.feature.own.review.query.FindReviewsByProductVariantIdOwnQuery;
import ing.beribtur.facade.api.feature.own.review.query.FindReviewsByRecordOwnQuery;

import java.util.List;

public interface ReviewOwnSeekFacade {
    //
    QueryResponse<Review> findReview(FindReviewOwnQuery query);
    QueryResponse<List<Review>> findReviewsByRecord(FindReviewsByRecordOwnQuery query);
    QueryResponse<List<Review>> findReviewsByVariantId(FindReviewsByProductVariantIdOwnQuery query);
}
