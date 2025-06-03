package ing.beribtur.aggregate.item.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.accent.util.JsonUtil;
import ing.beribtur.aggregate.item.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductCdo extends CreationDataObject {
    //
    private String ownerId; // Reference to User (Lender)
    private String title;
    private String description;
    private String categoryId; // Reference to ProductCategory
    private long sequence;

    public static ProductCdo fromJson(String json) {
        //
        return JsonUtil.fromJson(json, ProductCdo.class);
    }

    public static ProductCdo sample() {
        //
        return ProductCdo.builder()
                .ownerId("UUID")
                .title("My Sample Product")
                .description("Some lorem description for the product")
                .categoryId("UUID")
                .sequence(1L)
                .build();
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }

    public String genId() {
        //
        return Product.genId(ownerId, sequence);
    }

    public String toString() {
        //
        return toJson();
    }
}
