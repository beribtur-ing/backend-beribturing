package ing.beribtur.aggregate.rentalprovider.entity.sdo;

import ing.beribtur.aggregate.rentalprovider.entity.ItemImage;
import ing.beribtur.aggregate.shared.entity.User;
import ing.beribtur.aggregate.shared.entity.vo.Address;
import ing.beribtur.aggregate.shared.entity.vo.ItemCategory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class RentalItemCdo {
    private Long id;

    private String name;
    private String description;

    private ItemCategory category;

    private BigDecimal rentalPricePerDay;

    private String brand;
    private String conditionNotes;

    private boolean available;

    private User owner;

    private Address location;

    private LocalDateTime listedAt;

    private List<ItemImage> images;
}

