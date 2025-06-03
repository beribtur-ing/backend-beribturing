package ing.beribtur.aggregate.user.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.util.JsonUtil;
import ing.beribtur.aggregate.payment.entity.RentalDeposit;
import ing.beribtur.aggregate.payment.entity.vo.Discountable;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.rental.entity.Reservation;
import ing.beribtur.aggregate.report.entity.Report;
import ing.beribtur.aggregate.review.entity.Review;
import ing.beribtur.aggregate.user.entity.sdo.LendeeCdo;
import ing.beribtur.aggregate.user.entity.vo.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Lendee extends DomainEntity implements Discountable {
    //
    private String name;
    private String phoneNumber;
    private boolean active;
    private Profile profile;
    private long reservationSequence;

    // Domain relationships
    private transient List<Review> reviews;
    private transient List<Report> Reports;
    private transient List<RentalRecord> rentalRecords;
    private transient List<Reservation> reservations; // Products listed by the Lendee
    private transient List<RentalDeposit> deposits;

    public Lendee(LendeeCdo lendeeCdo) {
        //
        super(lendeeCdo.genId());
        BeanUtils.copyProperties(lendeeCdo, this);
        this.reservationSequence = 1L;
    }


    public static String genId(String accountId) {
        return accountId;
    }

    @Override
    protected void modifyAttributes(NameValueList nameValues) {
        for (NameValue nameValue : nameValues.list()) {
            String value = nameValue.getValue();
            switch (nameValue.getName().trim()) {
                case "name" -> this.name = value;
                case "phoneNumber" -> this.phoneNumber = value;
                case "active" -> this.active = Boolean.parseBoolean(value);
                case "profile" -> this.profile = JsonUtil.fromJson(value, Profile.class);
                default -> throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }
}
