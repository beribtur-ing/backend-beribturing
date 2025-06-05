package ing.beribtur.facade.api.feature.adm.review.rest;


import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.review.entity.Review;
import ing.beribtur.facade.api.feature.adm.review.query.FindReviewAdmQuery;
import ing.beribtur.facade.api.feature.adm.review.query.FindReviewsByRecordAdmQuery;

import java.util.List;

public interface ReviewAdmSeekFacade {
    //
    QueryResponse<Review> findReview(FindReviewAdmQuery query);
    QueryResponse<List<Review>> findReviewsByRecord(FindReviewsByRecordAdmQuery query);
}
