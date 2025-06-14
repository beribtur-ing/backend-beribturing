package ing.beribtur.facade.api.feature.own.item.query;

import ing.beribtur.accent.message.OffsetQueryRequest;
import ing.beribtur.feature.shared.sdo.ProductRdo;
import ing.beribtur.feature.shared.sdo.ProductSearchQdo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindProductRdosOwnQuery extends OffsetQueryRequest<List<ProductRdo>> {
    //

    // Text search fields
    private String searchKeyword;           // Search in title, description, brand, model

    // Product filters (Note: ownerId will be set from authentication context)
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

    public void validate() {
        //
        if (minPrice != null && maxPrice != null && minPrice.compareTo(maxPrice) > 0) {
            throw new IllegalArgumentException("minPrice cannot be greater than maxPrice");
        }

        if (availableFrom != null && availableUntil != null && availableFrom.isAfter(availableUntil)) {
            throw new IllegalArgumentException("availableFrom cannot be after availableUntil");
        }

        if (createdAfter != null && createdBefore != null && createdAfter.isAfter(createdBefore)) {
            throw new IllegalArgumentException("createdAfter cannot be after createdBefore");
        }

        if (updatedAfter != null && updatedBefore != null && updatedAfter.isAfter(updatedBefore)) {
            throw new IllegalArgumentException("updatedAfter cannot be after updatedBefore");
        }
    }

    public ProductSearchQdo toQdo() {
        //
        return ProductSearchQdo.builder()
            .searchKeyword(searchKeyword)
            .categoryIds(categoryIds)
            .brands(brands)
            .models(models)
            .manufacturers(manufacturers)
            .colors(colors)
            .materials(materials)
            .madeInCountries(madeInCountries)
            .producedYears(producedYears)
            .minPrice(minPrice)
            .maxPrice(maxPrice)
            .active(active)
            .availableFrom(availableFrom)
            .availableUntil(availableUntil)
            .isAvailable(isAvailable)
            .createdAfter(createdAfter)
            .createdBefore(createdBefore)
            .updatedAfter(updatedAfter)
            .updatedBefore(updatedBefore)
            .hasImages(hasImages)
            .hasVariants(hasVariants)
            .build();
    }
}
