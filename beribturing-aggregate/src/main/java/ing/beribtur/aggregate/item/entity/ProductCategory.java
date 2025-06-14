package ing.beribtur.aggregate.item.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.item.entity.sdo.ProductCategoryCdo;
import ing.beribtur.aggregate.payment.entity.vo.Discountable;
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
public class ProductCategory extends DomainEntity implements Discountable {
    //
    private String name;
    private String description;
    private String iconUrl;
    private String parentId; // Reference to parent category, if any
    private boolean active;

    // Domain relationships
    private transient List<ProductCategory> subCategories;

    public ProductCategory(ProductCategoryCdo productCategoryCdo) {
        //
        super(productCategoryCdo.genId());
        BeanUtils.copyProperties(productCategoryCdo, this);
        this.active = true;
    }

    @Override
    protected void modifyAttributes(NameValueList nameValueList) {
        //
        for (NameValue nameValue : nameValueList.list()) {
            String value = nameValue.getValue();
            switch (nameValue.getName()) {
                case "name":
                    this.name = value;
                    break;
                case "description":
                    this.description = value;
                    break;
                case "iconUrl":
                    this.iconUrl = value;
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
