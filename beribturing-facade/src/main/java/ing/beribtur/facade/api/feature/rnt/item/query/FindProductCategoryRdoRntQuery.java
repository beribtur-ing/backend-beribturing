package ing.beribtur.facade.api.feature.rnt.item.query;

import ing.beribtur.accent.message.QueryRequest;
import ing.beribtur.feature.shared.sdo.ProductCategoryRdo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindProductCategoryRdoRntQuery extends QueryRequest<ProductCategoryRdo> {
    //
    private String categoryId;

    public void validate() {
        //
        if (categoryId == null || categoryId.trim().isEmpty()) {
            throw new IllegalArgumentException("categoryId is required");
        }
    }
}
