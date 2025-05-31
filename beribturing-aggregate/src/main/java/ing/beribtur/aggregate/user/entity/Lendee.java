package ing.beribtur.aggregate.user.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.aggregate.payment.entity.RentalDeposit;
import ing.beribtur.aggregate.payment.entity.vo.Discountable;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.rental.entity.Reservation;
import ing.beribtur.aggregate.report.entity.Report;
import ing.beribtur.aggregate.review.entity.Review;
import ing.beribtur.aggregate.user.entity.vo.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Lendee extends DomainEntity implements Discountable {
    //
    private String name;
    private String phoneNumber;
    private String passwordHash;
    private boolean isActive;
    private Profile profile;

    // Domain relationships
    private transient List<Review> reviews;
    private transient List<Report> Reports;
    private transient List<RentalRecord> rentalRecords;
    private transient List<Reservation> reservations; // Products listed by the Lendee
    private transient List<RentalDeposit> deposits;

    public static String genId(String accountId) {
        return accountId;
    }
}
