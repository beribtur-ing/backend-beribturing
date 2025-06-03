package ing.beribtur.aggregate.review.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.review.entity.sdo.ReviewCdo;
import ing.beribtur.aggregate.user.entity.Lendee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Review extends DomainEntity {
    //
    private String reviewerId;
    private int rating;
    private String comment;
    private String recordId;

    // Domain relationships
    private transient Lendee reviewer;
    private transient RentalRecord record;

    public Review(ReviewCdo reviewCdo) {
        //
        super(reviewCdo.genId());
        BeanUtils.copyProperties(reviewCdo, this);
    }

    @Override
    protected void modifyAttributes(NameValueList nameValues) {
        for (NameValue nameValue : nameValues.list()) {
            String value = nameValue.getValue();
            switch (nameValue.getName().trim()) {
                case "reviewerId" -> this.reviewerId = value;
                case "rating" -> this.rating = Integer.parseInt(value);
                case "comment" -> this.comment = value;
                case "recordId" -> this.recordId = value;
                default -> throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }
}
