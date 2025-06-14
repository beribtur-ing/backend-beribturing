package ing.beribtur.facade.api.feature.rnt.item.query;

import ing.beribtur.accent.message.QueryRequest;
import ing.beribtur.feature.shared.sdo.ProductRdo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindProductRdoRntQuery extends QueryRequest<ProductRdo> {
    //
    private String productId;

    public void validate() {
        //
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("productId is required");
        }
    }
}
