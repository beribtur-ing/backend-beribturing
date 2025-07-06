package ing.beribtur.aggregate.item.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import ing.beribtur.aggregate.item.entity.ProductImage;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageCdo extends CreationDataObject {
    //
    private String variantId; // Reference to the product variant this image belongs to
    private String filename;
    private String originalFilename;
    private String url;
    private LocalDateTime expiresAt;
    private int order;
    private long sequence;

    public String genId() {
        //
        return ProductImage.genId(variantId, sequence);
    }
}
