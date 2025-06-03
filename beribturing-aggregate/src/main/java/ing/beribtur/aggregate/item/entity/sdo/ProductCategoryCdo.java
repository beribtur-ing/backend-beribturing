package ing.beribtur.aggregate.item.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.accent.util.JsonUtil;
import ing.beribtur.aggregate.item.entity.ProductCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductCategoryCdo extends CreationDataObject {
    //
    private String name;
    private String description;
    private String iconUrl;
    private String parentId; // Reference to parent category, if any


    public static ProductCategoryCdo fromJson(String json) {
        //
        return JsonUtil.fromJson(json, ProductCategoryCdo.class);
    }

    public static ProductCategoryCdo sample() {
        //
        return ProductCategoryCdo
                .builder()
                .name("My Sample ProductCategory")
                .description("Some lorem description for the productCategory")
                .iconUrl("https://example.com/icon.jpg")
                .parentId("UUID")
                .build();
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }

    public String genId() {
        //
        return super.genId();
    }

    public String toString() {
        //
        return toJson();
    }

}
