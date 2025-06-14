package ing.beribtur.facade.api.feature.adm.item.query;

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
public class FindProductCategoryRdosAdmQuery extends OffsetQueryRequest<List<ProductCategoryRdo>> {
    //
    private String searchKeyword;

    public void validate() {
        //
    }
}
