package ing.beribtur.facade.api.feature.rnt.review.rest;


import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.review.entity.Review;
import ing.beribtur.facade.api.feature.rnt.review.query.FindReviewRntQuery;
import ing.beribtur.facade.api.feature.rnt.review.query.FindReviewsByRecordRntQuery;

import java.util.List;

public interface ReviewRntSeekFacade {
    //
    QueryResponse<Review> findReview(FindReviewRntQuery query);
    QueryResponse<List<Review>> findReviewsByRecord(FindReviewsByRecordRntQuery query);
}
