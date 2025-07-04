package ing.beribtur.feature.shared.sdo;

import ing.beribtur.aggregate.item.entity.ProductImage;
import ing.beribtur.aggregate.item.entity.vo.PriceUnit;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class ProductRentalRecordRdo {
    //from Currency of Price
    private final BigDecimal amount;
    private final String currency;
    //from product;
    private String productId;
    private String title;
    private String description;
    //from productCategory
    private String categoryId;
    private String name;
    //from productVariant
    private String productVariantId;
    private String model;
    private PriceUnit unit;
    private List<ProductImage> images;
}
