package ing.beribtur.facade.api.feature.rnt.item.query;

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
public class FindProductRdosRntQuery extends OffsetQueryRequest<List<ProductRdo>> {
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

    // Availability filters - important for renters to see available products
    private LocalDateTime availableFrom;    // Available from date
    private LocalDateTime availableUntil;   // Available until date
    private Boolean isAvailable;            // Filter by current availability (defaults to true)

    public void validate() {
        //
        if (minPrice != null && maxPrice != null && minPrice.compareTo(maxPrice) > 0) {
            throw new IllegalArgumentException("minPrice cannot be greater than maxPrice");
        }

        if (availableFrom != null && availableUntil != null && availableFrom.isAfter(availableUntil)) {
            throw new IllegalArgumentException("availableFrom cannot be after availableUntil");
        }
    }

    public ProductSearchQdo toQdo() {
        //
        return ProductSearchQdo.builder()
            .searchKeyword(searchKeyword)
            .ownerIds(ownerIds)
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
            .active(true)  // Default to active products for renters
            .availableFrom(availableFrom)
            .availableUntil(availableUntil)
            .isAvailable(isAvailable != null ? isAvailable : true)  // Default to available products
            .build();
    }
}
