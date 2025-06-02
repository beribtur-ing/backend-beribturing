package ing.beribtur.storejpa.aggregate.review;

import ing.beribtur.aggregate.review.entity.Review;
import ing.beribtur.aggregate.review.store.ReviewStore;
import ing.beribtur.storejpa.aggregate.review.jpo.ReviewJpo;
import ing.beribtur.storejpa.aggregate.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewJpaStore implements ReviewStore {

    private final ReviewRepository reviewRepository;

    @Override
    public void create(Review review) {
        ReviewJpo reviewJpo = new ReviewJpo(review);
        reviewRepository.save(reviewJpo);
        review.setId(reviewJpo.getId());
    }

    @Override
    public Review retrieve(String id) {
        ReviewJpo reviewJpo = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found: " + id));
        return reviewJpo.toDomain();
    }

    @Override
    public List<Review> retrieveAll(List<String> ids) {
        List<ReviewJpo> jpos = reviewRepository.findAllById(ids);
        return ReviewJpo.toDomains(jpos);
    }

    @Override
    public void update(Review review) {
        ReviewJpo existingJpo = reviewRepository.findById(review.getId())
                .orElseThrow(() -> new IllegalArgumentException("Review not found: " + review.getId()));

        ReviewJpo updatedJpo = new ReviewJpo(review);
        updatedJpo.setEntityVersion(existingJpo.getEntityVersion());
        updatedJpo.setRegisteredBy(existingJpo.getRegisteredBy());
        updatedJpo.setRegisteredOn(existingJpo.getRegisteredOn());

        reviewRepository.save(updatedJpo);
    }

    @Override
    public void delete(String id) {
        reviewRepository.deleteById(id);
    }

    // Optional: Custom query methods
    public List<Review> findByReviewerId(String reviewerId) {
        return ReviewJpo.toDomains(reviewRepository.findByReviewerId(reviewerId));
    }

    public List<Review> findByRecordId(String recordId) {
        return ReviewJpo.toDomains(reviewRepository.findByRecordId(recordId));
    }

    public List<Review> findByRating(int rating) {
        return ReviewJpo.toDomains(reviewRepository.findByRating(rating));
    }
}

