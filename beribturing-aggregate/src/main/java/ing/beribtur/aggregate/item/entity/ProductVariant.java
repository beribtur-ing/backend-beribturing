package ing.beribtur.aggregate.item.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.aggregate.item.entity.vo.Price;
import ing.beribtur.aggregate.item.entity.vo.ProductAvailability;
import ing.beribtur.aggregate.item.entity.vo.Size;
import ing.beribtur.aggregate.payment.entity.vo.Discountable;
import ing.beribtur.aggregate.rental.entity.Reservation;

import java.util.List;
import java.util.UUID;

public class ProductVariant extends DomainEntity implements Discountable {
    //
    private UUID productId;
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
    private boolean isActive;

    private String notes;                           // Optional extra info

    // Domain relationships
    private transient Product product;
    private transient List<Reservation> reservations;
    private transient List<ProductImage> images;
}
