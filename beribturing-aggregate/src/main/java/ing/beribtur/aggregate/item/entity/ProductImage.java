package ing.beribtur.aggregate.item.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.item.entity.sdo.ProductImageCdo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage extends DomainEntity {
    //
    private String variantId; // Reference to the product variant this image belongs to
    private String url;
    private int order;
    private boolean active;
    private LocalDateTime expiresAt;

    // Domain relationships
    private transient ProductVariant variant;

    public ProductImage(ProductImageCdo productImageCdo) {
        //
        super(productImageCdo.genId());
        BeanUtils.copyProperties(productImageCdo, this);
        this.active = true;
    }

    public static String genId(String variantId, long sequence) {
        //
        return String.format("%s-%d", variantId, sequence);
    }

    @Override
    protected void modifyAttributes(NameValueList nameValueList) {
        //
        for (NameValue nameValue : nameValueList.list()) {
            String value = nameValue.getValue();
            switch (nameValue.getName()) {
                case "url":
                    this.url = value;
                    break;
                case "order":
                    this.order = Integer.parseInt(value);
                    break;
                case "active":
                    this.active = Boolean.parseBoolean(value);
                    break;
                case "expiresAt":
                    this.expiresAt = LocalDateTime.parse(value);
                    break;
                default:
                    throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }
}
