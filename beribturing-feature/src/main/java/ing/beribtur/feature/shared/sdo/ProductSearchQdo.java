package ing.beribtur.feature.shared.sdo;

import ing.beribtur.aggregate.item.entity.vo.PriceUnit;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ProductSearchQdo {
    //

    // Text search fields
    private String searchKeyword;           // Search in title, description, brand, model

    // Product filters
    private List<String> ownerIds;          // Filter by multiple owners
    private List<String> categoryIds;       // Filter by multiple categories

    // Product variant filters
    private List<String> brands;            // Filter by multiple brands
    private List<String> models;            // Filter by multiple models
    private List<String> manufacturers;     // Filter by multiple manufacturers
    private List<String> colors;            // Filter by multiple colors
    private List<String> materials;         // Filter by multiple materials
    private List<String> madeInCountries;   // Filter by multiple countries of origin
    private List<String> producedYears;     // Filter by multiple years

    // Price filters
    private BigDecimal minPrice;            // Minimum price
    private BigDecimal maxPrice;            // Maximum price
    private PriceUnit priceUnit;            // Filter by price unit (HOURLY, DAILY, WEEKLY)

    // Availability filters
    private Boolean active;                 // Filter by active status
    private LocalDateTime availableFrom;    // Available from date
    private LocalDateTime availableUntil;   // Available until date
    private Boolean isAvailable;            // Filter by current availability

    // Date filters
    private LocalDateTime createdAfter;     // Products created after date
    private LocalDateTime createdBefore;    // Products created before date
    private LocalDateTime updatedAfter;     // Products updated after date
    private LocalDateTime updatedBefore;    // Products updated before date

    // Administrative filters
    private Boolean hasImages;              // Filter products with/without images
    private Boolean hasVariants;            // Filter products with/without variants
}
