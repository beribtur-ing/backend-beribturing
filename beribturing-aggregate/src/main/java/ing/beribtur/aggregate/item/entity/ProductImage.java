package ing.beribtur.aggregate.item.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.item.entity.sdo.ProductImageCdo;
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
public class ProductImage extends DomainEntity {
    //
    private String variantId; // Reference to the product variant this image belongs to
    private String url;
    private int order;

    // Domain relationships
    private transient ProductVariant variant;

    public ProductImage(ProductImageCdo productImageCdo) {
        //
        super(productImageCdo.genId());
        BeanUtils.copyProperties(productImageCdo, this);
    }

    public static String genId(String variantId, long sequence) {
        //
        return String.format("%s-%d", variantId, sequence);
    }

    public static ProductImage fromJson(String json) {
        //
        return JsonUtil.fromJson(json, ProductImage.class);
    }

    public static ProductImage sample() {
        //
        return new ProductImage(ProductImageCdo.sample());
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    @Override
    protected void modifyAttributes(NameValueList var1) {
        //
        for (NameValue nameValue : var1.list()) {
            String value = nameValue.getValue();
            switch(nameValue.getName()) {
                case "url":
                    this.url = value;
                    break;
                case "order":
                    this.order = Integer.parseInt(value);
                    break;
                default:
                    throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }
}
