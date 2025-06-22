package ing.beribtur.feature.shared.sdo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class PopularProductRdo {
    //
    //from ProductJpo
    private String title;

    //from ReviewJpo
    private int averageRating;
    private int reviewCount;

    //from ProductImageJpo
    private String url;

    //Price fields from ProductVariantJpo
    private BigDecimal priceAmount;
    private String priceCurrency;
    private String priceUnit;

    //Profile field from LenderJpo
    private String address;

}
