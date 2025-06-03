package ing.beribtur.aggregate.item.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.item.entity.vo.Price;
import ing.beribtur.aggregate.item.entity.vo.ProductAvailability;
import ing.beribtur.accent.util.JsonUtil;
import ing.beribtur.aggregate.item.entity.ProductVariant;
import ing.beribtur.aggregate.item.entity.vo.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
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
    private boolean isActive;
    private long sequence;

    public static ProductVariantCdo fromJson(String json) {
        //
        return JsonUtil.fromJson(json, ProductVariantCdo.class);
    }

    public static ProductVariantCdo sample() {
        //
        return ProductVariantCdo.builder()
        .productId("UUID")
        .price(Price.sample())
        .size(Size.sample())
        .color("My Sample Color")
        .brand("My Sample Brand")
        .model("My Sample Model")
        .manufacturer("My Sample Manufacturer")
        .madeIn("My Sample MadeIn")
        .producedYear("My Sample ProducedYear")
        .material("My Sample Material")
        .manual("My Sample Manual")
        .availability(ProductAvailability.sample())
        .isActive(true)
        .sequence(1L)
        .build();
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }

    public String genId() {
        //
        return ProductVariant.genId(productId, sequence);
    }

    public String toString() {
        //
        return toJson();
    }
}
