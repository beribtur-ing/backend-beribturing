package ing.beribtur.aggregate.item.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductImageCdo extends CreationDataObject {
    //
    private String variantId; // Reference to the product variant this image belongs to
    private String url;
    private int order;
    private long sequence;

    public static ProductImageCdo fromJson(String json) {
        //
        return JsonUtil.fromJson(json, ProductImageCdo.class);
    }

    public static ProductImageCdo sample() {
        //
        return ProductImageCdo.builder()
                .variantId("UUID")
                .url("https://example.com/image.jpg")
                .order(1)
                .sequence(1L)
                .build();
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
    
    

}
