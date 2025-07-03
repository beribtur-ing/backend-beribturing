package ing.beribtur.feature.shared.sdo;

import ing.beribtur.accent.util.JsonSerializable;
import ing.beribtur.aggregate.item.entity.vo.Size;
import ing.beribtur.aggregate.rental.entity.vo.Period;
import ing.beribtur.aggregate.rental.entity.vo.ReservationStatus;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationRdo implements JsonSerializable {
    //
    private String id;
    private String ownerName;
    private String requesterName;
    private Period period;
    private ReservationStatus status;
    private String productId;
    private String productName;
    private String variantId;
    private String variantBrand;
    private String variantModel;
    private String variantColor;
    private Size variantSize;
}
