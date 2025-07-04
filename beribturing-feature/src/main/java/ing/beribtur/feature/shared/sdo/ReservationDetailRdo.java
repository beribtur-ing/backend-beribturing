package ing.beribtur.feature.shared.sdo;

import ing.beribtur.accent.util.JsonSerializable;
import ing.beribtur.aggregate.item.entity.Product;
import ing.beribtur.aggregate.item.entity.ProductCategory;
import ing.beribtur.aggregate.item.entity.ProductImage;
import ing.beribtur.aggregate.item.entity.ProductVariant;
import ing.beribtur.aggregate.rental.entity.Reservation;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.entity.Lender;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDetailRdo implements JsonSerializable {
    //
    private Reservation reservation;
    private Lendee requester;
    private Lender owner;
    private Product product;
    private ProductCategory category;
    private ProductVariant variant;
    private List<ProductImage> images;
}
