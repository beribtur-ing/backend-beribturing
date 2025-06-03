package ing.beribtur.aggregate.item.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.item.entity.vo.Price;
import ing.beribtur.aggregate.item.entity.vo.ProductAvailability;
import ing.beribtur.aggregate.item.entity.vo.Size;
import ing.beribtur.aggregate.payment.entity.vo.Discountable;
import ing.beribtur.aggregate.rental.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariant extends DomainEntity implements Discountable {
    //
    private String productId;
    private Price price;
    private Size size;                              // For clothing/accessories
    private String color;
    private String brand;
    private String model;
    private String manufacturer;
    private String madeIn;                          //country
    private String producedYear;
    private String material;
    private String manual;
    private ProductAvailability availability;
    private boolean active;

    private String notes;                           // Optional extra info

    // Domain relationships
    private transient Product product;
    private transient List<Reservation> reservations;
    private transient List<ProductImage> images;

    @Override
    protected void modifyAttributes(NameValueList var1) {

    }
}
