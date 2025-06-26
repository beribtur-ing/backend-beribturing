package ing.beribtur.feature.shared.sdo;

import ing.beribtur.aggregate.payment.entity.RentalDeposit;
import ing.beribtur.aggregate.payment.entity.vo.Currency;
import ing.beribtur.aggregate.rental.entity.vo.Period;
import ing.beribtur.aggregate.rental.entity.vo.RentalStatus;
import ing.beribtur.aggregate.user.entity.Lendee;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentalRecordRdo {
    //
    //from RentalRecord
    private String id;
    //from Lendee
    private Lendee lendee;
    //from ProductVariant
    private Period period;
    private LocalDateTime rentedAt;
    private LocalDateTime returnedAt;
    //from Product
    private ProductRentalRecordRdo productRentalRecordRdo;
    //from RentalRecord
    private Currency fee;
    //from RentalDeposit
    private RentalDeposit rentalDeposit;
    //from RentalRecord
    private RentalStatus status;
}
