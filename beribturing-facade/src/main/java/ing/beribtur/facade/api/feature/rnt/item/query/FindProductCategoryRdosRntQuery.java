package ing.beribtur.facade.api.feature.rnt.item.query;

import ing.beribtur.accent.message.OffsetQueryRequest;
import ing.beribtur.feature.shared.sdo.ProductCategoryRdo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindProductCategoryRdosRntQuery extends OffsetQueryRequest<List<ProductCategoryRdo>> {
    //
    private String searchKeyword;           // Search in category name and description

    public void validate() {
        //
        // No specific validation needed for optional search keyword
    }
}
