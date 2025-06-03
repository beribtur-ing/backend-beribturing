package ing.beribtur.aggregate.item.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.item.entity.sdo.ProductCategoryCdo;
import ing.beribtur.accent.util.JsonUtil;
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

    // Domain relationships
    private transient List<ProductCategory> subCategories;

    public ProductCategory(ProductCategoryCdo productCategoryCdo) {
        //
        super(productCategoryCdo.genId());
        BeanUtils.copyProperties(productCategoryCdo, this);
    }

    public static ProductCategory fromJson(String json) {
        //
        return JsonUtil.fromJson(json, ProductCategory.class);
    }

    public static ProductCategory sample() {
        //
        return new ProductCategory(ProductCategoryCdo.sample());
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }

    @Override
    public String toString() {
        //
        return toJson();
    }


    @Override
    protected void modifyAttributes(NameValueList nameValues) {
        //
        for (NameValue nameValue : nameValues.list()) {
            String value = nameValue.getValue();
            switch(nameValue.getName()) {
                case "name":
                    this.name = value;
                    break;
                case "description":
                    this.description = value;
                    break;
                case "iconUrl":
                    this.iconUrl = value;
                    break;
                default:
                    throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }

}
