package ing.beribtur.aggregate.item.entity;

import java.util.List;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.item.entity.vo.Price;
import ing.beribtur.aggregate.item.entity.vo.ProductAvailability;
import ing.beribtur.aggregate.item.entity.vo.Size;
import ing.beribtur.aggregate.payment.entity.vo.Discountable;
import ing.beribtur.aggregate.rental.entity.Reservation;
import ing.beribtur.aggregate.item.entity.sdo.ProductVariantCdo;
import ing.beribtur.accent.util.JsonUtil;
import org.springframework.beans.BeanUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private boolean isActive;
    private long imageSequence;

    private String notes;                           // Optional extra info

    // Domain relationships
    private transient Product product;
    private transient List<Reservation> reservations;
    private transient List<ProductImage> images;

    public ProductVariant(ProductVariantCdo productVariantCdo) {
        //
        super(productVariantCdo.genId());
        BeanUtils.copyProperties(productVariantCdo, this);
        this.imageSequence = 1L;
    }

    public static String genId(String productId, long sequence) {
        //
        return String.format("%s-%d", productId, sequence);
    }

    @Override
    protected void modifyAttributes(NameValueList vanameValueList) {
        //
        for (NameValue nameValue : vanameValueList.list()) {
            String value = nameValue.getValue();
            switch (nameValue.getName()) {
                case "price":
                    this.price = JsonUtil.fromJson(value, Price.class);
                    break;
                case "size":
                    this.size = JsonUtil.fromJson(value, Size.class);
                    break;
                case "color":
                    this.color = value;
                    break;
                case "brand":
                    this.brand = value;
                    break;
                case "model":
                    this.model = value;
                    break;
                case "manufacturer":
                    this.manufacturer = value;
                    break;
                case "madeIn":
                    this.madeIn = value;
                    break;
                case "producedYear":
                    this.producedYear = value;
                    break;
                case "material":
                    this.material = value;
                    break;
                case "manual":
                    this.manual = value;
                    break;
                case "availability":
                    this.availability = JsonUtil.fromJson(value, ProductAvailability.class);
                    break;
                case "isActive":
                    this.isActive = Boolean.parseBoolean(value);
                    break;
                case "imageSequence":
                    this.imageSequence = Long.parseLong(value);
                    break;
                case "notes":
                    this.notes = value;
                    break;
                default:
                    throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }
}
