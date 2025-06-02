package ing.beribtur.storejpa.aggregate.review.repository;

import ing.beribtur.storejpa.aggregate.review.jpo.ReviewJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewJpo, String> {
    List<ReviewJpo> findByReviewerId(String reviewerId);
    List<ReviewJpo> findByRecordId(String recordId);
    List<ReviewJpo> findByRating(int rating);
}

