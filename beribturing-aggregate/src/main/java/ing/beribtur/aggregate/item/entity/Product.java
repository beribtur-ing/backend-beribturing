package ing.beribtur.aggregate.item.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.item.entity.sdo.ProductCdo;
import ing.beribtur.aggregate.payment.entity.vo.Discountable;
import ing.beribtur.aggregate.user.entity.Lender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends DomainEntity implements Discountable {
    //
    private String ownerId; // Reference to User (Lender)
    private String title;
    private String description;
    private String categoryId; // Reference to ProductCategory
    private long variantSequence;
    private boolean active;

    // Domain relationships
    private transient Lender owner;
    private transient ProductCategory category;
    private transient List<ProductVariant> variants;

    public Product(ProductCdo productCdo) {
        //
        super(productCdo.genId());
        BeanUtils.copyProperties(productCdo, this);
        this.variantSequence = 1L;
        this.active = true;
    }


    public static String genId(String ownerId, long sequence) {
        //
        return String.format("%s-%d", ownerId, sequence);
    }

    @Override
    protected void modifyAttributes(NameValueList nameValueList) {
        //
        for (NameValue nameValue : nameValueList.list()) {
            String value = nameValue.getValue();
            switch (nameValue.getName()) {
                case "title":
                    this.title = value;
                    break;
                case "description":
                    this.description = value;
                    break;
                case "categoryId":
                    this.categoryId = value;
                    break;
                case "variantSequence":
                    this.variantSequence = Long.parseLong(value);
                    break;
                case "active":
                    this.active = Boolean.parseBoolean(value);
                    break;
                default:
                    throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }
}

