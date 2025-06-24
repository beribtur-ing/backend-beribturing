package ing.beribtur.storejpa.aggregate.item.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.item.entity.ProductVariant;
import ing.beribtur.aggregate.item.entity.vo.Price;
import ing.beribtur.aggregate.item.entity.vo.PriceUnit;
import ing.beribtur.aggregate.item.entity.vo.ProductAvailability;
import ing.beribtur.aggregate.item.entity.vo.Size;
import ing.beribtur.aggregate.payment.entity.vo.Currency;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PRODUCT_VARIANT")
public class ProductVariantJpo extends DomainEntityJpo {

    @Column(nullable = false)
    private String productId;

    // Price fields
    private BigDecimal priceAmount;
    private String priceCurrency;
    private String priceUnit; // HOURLY, DAILY, WEEKLY

    // Size fields
    private String sizeLabel;
    private Double sizeWidth;
    private Double sizeHeight;
    private Double sizeDepth;
    private Double sizeWeight;
    private String sizeMeasureUnit;

    private String color;
    private String brand;
    private String model;
    private String manufacturer;
    private String madeIn;
    private String producedYear;
    private String material;
    private String manual;

    // ProductAvailability fields
    private LocalDateTime availableFrom;
    private LocalDateTime availableUntil;
    private String availableDays; // Comma-separated list of DayOfWeek values

    private boolean active;
    private String notes;
    private long imageSequence;

    public ProductVariantJpo(ProductVariant productVariant) {
        //
        super(productVariant);
        BeanUtils.copyProperties(productVariant, this);

        // Handle Price
        if (productVariant.getPrice() != null) {
            Price price = productVariant.getPrice();
            if (price.getCurrency() != null) {
                this.priceAmount = price.getCurrency().getAmount();
                this.priceCurrency = price.getCurrency().getCurrency();
            }
            if (price.getUnit() != null) {
                this.priceUnit = price.getUnit().name();
            }
        }

        // Handle Size
        if (productVariant.getSize() != null) {
            Size size = productVariant.getSize();
            this.sizeLabel = size.getLabel();
            this.sizeWidth = size.getWidth();
            this.sizeHeight = size.getHeight();
            this.sizeDepth = size.getDepth();
            this.sizeWeight = size.getWeight();
            this.sizeMeasureUnit = size.getMeasureUnit();
        }

        // Handle ProductAvailability
        if (productVariant.getAvailability() != null) {
            ProductAvailability availability = productVariant.getAvailability();
            this.availableFrom = availability.getAvailableFrom();
            this.availableUntil = availability.getAvailableUntil();
            if (availability.getAvailableDays() != null) {
                this.availableDays = availability.getAvailableDays().stream()
                        .map(DayOfWeek::name)
                        .collect(Collectors.joining(","));
            }
        }
    }

    public ProductVariant toDomain() {
        //
        ProductVariant productVariant = new ProductVariant();
        BeanUtils.copyProperties(this, productVariant);

        // Reconstruct Price
        Price price = new Price();
        if (this.priceAmount != null && this.priceCurrency != null) {
            Currency currency = new Currency(this.priceAmount, this.priceCurrency);
            price.setCurrency(currency);
        }
        if (this.priceUnit != null) {
            price.setUnit(PriceUnit.valueOf(this.priceUnit));
        }
        productVariant.setPrice(price);

        // Reconstruct Size
        Size size = new Size();
        size.setLabel(this.sizeLabel);
        size.setWidth(this.sizeWidth);
        size.setHeight(this.sizeHeight);
        size.setDepth(this.sizeDepth);
        size.setWeight(this.sizeWeight);
        size.setMeasureUnit(this.sizeMeasureUnit);
        productVariant.setSize(size);

        // Reconstruct ProductAvailability
        ProductAvailability availability = new ProductAvailability();
        availability.setAvailableFrom(this.availableFrom);
        availability.setAvailableUntil(this.availableUntil);
        if (this.availableDays != null && !this.availableDays.isEmpty()) {
            List<DayOfWeek> days = Arrays.stream(this.availableDays.split(","))
                    .map(DayOfWeek::valueOf)
                    .collect(Collectors.toList());
            availability.setAvailableDays(days);
        }
        productVariant.setAvailability(availability);

        return productVariant;
    }

    public static List<ProductVariant> toDomains(List<ProductVariantJpo> jpos) {
        //
        return jpos.stream().map(ProductVariantJpo::toDomain).toList();
    }
}
