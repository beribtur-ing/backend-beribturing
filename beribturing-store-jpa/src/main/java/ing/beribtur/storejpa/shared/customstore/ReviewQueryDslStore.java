package ing.beribtur.storejpa.shared.customstore;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.review.entity.Review;
import ing.beribtur.feature.shared.customstore.ReviewCustomStore;
import ing.beribtur.storejpa.aggregate.review.jpo.ReviewJpo;
import ing.beribtur.storejpa.util.QuerydslUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

import static ing.beribtur.storejpa.aggregate.rental.jpo.QRentalRecordJpo.rentalRecordJpo;
import static ing.beribtur.storejpa.aggregate.review.jpo.QReviewJpo.reviewJpo;


@Repository
@RequiredArgsConstructor
public class ReviewQueryDslStore implements ReviewCustomStore {
    //
    private final QuerydslUtil querydslUtil;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Review> findReviewsByProductVariantId(String productVariantId, Offset offset) {
        //

        Predicate[] predicates = {
                reviewJpo.recordId.in(
                        JPAExpressions
                                .select(rentalRecordJpo.id)
                                .from(rentalRecordJpo)
                                .where(rentalRecordJpo.productVariantId.eq(productVariantId))
                )
        };

        OrderSpecifier<?>[] orderSpecifiers = {
                reviewJpo.registeredOn.desc()
        };

        JPAQuery<?> from = jpaQueryFactory
                .from(reviewJpo)
                .join(rentalRecordJpo).on(reviewJpo.recordId.eq(rentalRecordJpo.id));

        Page<ReviewJpo> reviewJpoPage = querydslUtil.query(reviewJpo, reviewJpo, predicates, orderSpecifiers, offset);

        List<Review> reviews = reviewJpoPage.getContent().stream()
                .map(ReviewJpo::toDomain)
                .toList();

        return new PageImpl<>(reviews, reviewJpoPage.getPageable(), reviewJpoPage.getTotalElements());
    }
}
