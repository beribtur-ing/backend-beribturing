package ing.beribtur.aggregate.review.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.user.entity.Lendee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

    @Override
    protected void modifyAttributes(NameValueList var1) {

    }
}
