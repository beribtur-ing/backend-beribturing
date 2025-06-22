package ing.beribtur.facade.api.feature.rnt.item.query;

import ing.beribtur.accent.message.OffsetQueryRequest;
import ing.beribtur.feature.shared.sdo.PopularProductRdo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindPopularProductRdosRntQuery extends OffsetQueryRequest<List<PopularProductRdo>> {
    //
    private Integer maxCount;

    public void validate() {
        //
    }
}
