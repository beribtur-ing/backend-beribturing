package ing.beribtur.storejpa.aggregate.review.repository;

import ing.beribtur.storejpa.aggregate.review.jpo.ReviewJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewJpo, String> {
    //
    List<ReviewJpo> findByReviewerIdAndVisibleTrue(String reviewerId);
    List<ReviewJpo> findByRecordIdAndVisibleTrue(String recordId);
    List<ReviewJpo> findByRatingAndVisibleTrue(int rating);
}

