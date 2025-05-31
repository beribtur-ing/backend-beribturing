package ing.beribtur.aggregate.review.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.user.entity.Landee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Review extends DomainEntity {
    //
    private UUID reviewerId;
    private int rating;
    private String comment;

    // Domain relationships
    private transient Landee reviewer;
    private transient RentalRecord record;
}
