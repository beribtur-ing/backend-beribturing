package ing.beribtur.aggregate.item.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.item.entity.ProductVariant;
import ing.beribtur.aggregate.item.entity.vo.Price;
import ing.beribtur.aggregate.item.entity.vo.ProductAvailability;
import ing.beribtur.aggregate.item.entity.vo.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariantCdo extends CreationDataObject {
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
    private long sequence;

    public String genId() {
        //
        return ProductVariant.genId(productId, sequence);
    }
}
