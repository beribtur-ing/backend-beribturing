package ing.beribtur.storejpa.aggregate.review.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.review.entity.Review;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "REVIEW")
public class ReviewJpo extends DomainEntityJpo {

    @Column(nullable = false)
    private String reviewerId;

    @Column(nullable = false)
    private int rating;

    @Column(length = 1000)
    private String comment;

    @Column(nullable = false)
    private String recordId;

    public ReviewJpo(Review review) {
        //
        super(review);
        BeanUtils.copyProperties(review, this);
    }

    public Review toDomain() {
        //
        Review review = new Review();
        BeanUtils.copyProperties(this, review);
        return review;
    }

    public static List<Review> toDomains(List<ReviewJpo> jpos) {
        //
        return jpos.stream().map(ReviewJpo::toDomain).toList();
    }
}
