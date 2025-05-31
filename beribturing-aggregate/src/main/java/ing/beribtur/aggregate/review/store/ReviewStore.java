package ing.beribtur.aggregate.review.store;


import ing.beribtur.aggregate.review.entity.Review;

import java.util.List;

public interface ReviewStore {
    //
    void create(Review review);
    Review retrieve(String id);
    List<Review> retrieveAll(List<String> ids);
    void update(Review review);
    void delete(String id);
}
