package ing.beribtur.aggregate.review.logic;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.util.Entities;
import ing.beribtur.aggregate.review.entity.Review;
import ing.beribtur.aggregate.review.entity.sdo.ReviewCdo;
import ing.beribtur.aggregate.review.store.ReviewStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewLogic {
    //
    private final ReviewStore reviewStore;

    public String registerReview(ReviewCdo reviewCdo) {
        //
        Review review = new Review(reviewCdo);
        reviewStore.create(review);
        return review.getId();
    }

    public List<String> registerReviews(List<ReviewCdo> reviewCdos) {
        //
        return reviewCdos.stream()
                .map(this::registerReview)
                .collect(Collectors.toList());
    }

    public Review findReview(String reviewId) {
        //
        return reviewStore.retrieve(reviewId);
    }

    public List<Review> findReviews(List<String> reviewIds) {
        //
        return reviewStore.retrieveAll(reviewIds);
    }

    public List<Review> findReviews(Offset offset) {
        //
        return reviewStore.retrieveList(offset);
    }

    public void modifyReview(String reviewId, NameValueList nameValues) {
        //
        Review review = findReview(reviewId);
        review.modify(nameValues);
        reviewStore.update(review);
    }

    public void modifyReview(Review review) {
        //
        Review oldReview = findReview(review.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldReview, review);
        if (!nameValues.list().isEmpty()) {
            modifyReview(review.getId(), nameValues);
        }
    }

    public void removeReview(String reviewId) {
        //
        reviewStore.delete(reviewId);
    }

    public boolean existsReview(String reviewId) {
        //
        return reviewStore.exists(reviewId);
    }

    public List<Review> findReviewsByReviewerId(String reviewerId) {
        //
        return reviewStore.retrieveByReviewerId(reviewerId);
    }

    public List<Review> findReviewsByRecordId(String recordId) {
        //
        return reviewStore.retrieveByRecordId(recordId);
    }

    public List<Review> findReviewsByRating(int rating) {
        //
        return reviewStore.retrieveByRating(rating);
    }

    public void hideReview(String reviewId) {
        //
        Review review = findReview(reviewId);
        if (!review.isVisible()) {
            throw new IllegalStateException("Review is already hidden.");
        }
        review.setVisible(false);
        reviewStore.update(review);
    }
}

