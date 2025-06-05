package ing.beribtur.storejpa.aggregate.review;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.review.entity.Review;
import ing.beribtur.aggregate.review.store.ReviewStore;
import ing.beribtur.storejpa.aggregate.review.jpo.ReviewJpo;
import ing.beribtur.storejpa.aggregate.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
public class ReviewJpaStore implements ReviewStore {
    //
    private final ReviewRepository reviewRepository;

    @Override
    public void create(Review review) {
        //
        ReviewJpo reviewJpo = new ReviewJpo(review);
        reviewRepository.save(reviewJpo);
    }

    @Override
    public void createAll(List<Review> reviews) {
        //
        if (reviews == null) {
            return;
        }
        reviews.forEach(this::create);
    }

    @Override
    public Review retrieve(String id) {
        //
        return reviewRepository.findById(id)
                .map(ReviewJpo::toDomain)
                .orElse(null);
    }

    @Override
    public List<Review> retrieveAll(List<String> reviewIds) {
        //
        Iterable<ReviewJpo> allById = reviewRepository.findAllById(reviewIds);
        return ReviewJpo.toDomains(StreamSupport.stream(allById.spliterator(), false).collect(Collectors.toList()));
    }

    @Override
    public List<Review> retrieveList(Offset offset) {
        //
        Pageable pageable = PageRequest.of(offset.page(), offset.limit());
        return reviewRepository.findAll(pageable)
                .map(ReviewJpo::toDomain)
                .toList();
    }

    @Override
    public void update(Review review) {
        //
        ReviewJpo reviewJpo = new ReviewJpo(review);
        reviewRepository.save(reviewJpo);
    }

    @Override
    public void delete(Review review) {
        //
        reviewRepository.deleteById(review.getId());
    }

    @Override
    public void delete(String id) {
        //
        reviewRepository.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        //
        return reviewRepository.existsById(id);
    }

    @Override
    public List<Review> retrieveByReviewerId(String reviewerId) {
        //
        return ReviewJpo.toDomains(reviewRepository.findByReviewerIdAndVisibleTrue(reviewerId));
    }

    @Override
    public List<Review> retrieveByRecordId(String recordId) {
        //
        return ReviewJpo.toDomains(reviewRepository.findByRecordIdAndVisibleTrue(recordId));
    }

    @Override
    public List<Review> retrieveByRating(int rating) {
        //
        return ReviewJpo.toDomains(reviewRepository.findByRatingAndVisibleTrue(rating));
    }
}

