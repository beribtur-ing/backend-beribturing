package ing.beribtur.aggregate.review.store;


import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.review.entity.Review;

import java.util.List;

public interface ReviewStore {
    //
    void create(Review review);
    void createAll(List<Review> reviews);
    Review retrieve(String id);
    List<Review> retrieveAll(List<String> reviewIds);
    List<Review> retrieveList(Offset offset);
    void update(Review review);
    void delete(Review review);
    void delete(String id);
    boolean exists(String id);

    List<Review> retrieveByReviewerId(String reviewerId);
    List<Review> retrieveByRecordId(String recordId);
    List<Review> retrieveByRating(int rating);
}
