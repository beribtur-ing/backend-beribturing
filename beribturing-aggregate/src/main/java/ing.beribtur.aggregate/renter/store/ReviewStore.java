package ing.beribtur.aggregate.renter.store;


import ing.beribtur.aggregate.renter.entity.Review;

import java.util.List;

public interface ReviewStore {
    //
    Review create(Review payment);
    Review retrieve(Long id);
    List<Review> retrieveAll(List<Long> ids);
    Review update(Review review);
    void delete(Long id);
}
