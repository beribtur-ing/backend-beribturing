package ing.beribtur.facade.api.feature.adm.lender.query;

import ing.beribtur.accent.message.OffsetQueryRequest;
import ing.beribtur.aggregate.user.entity.Lender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FindLendersAdmQuery extends OffsetQueryRequest<List<Lender>> {
    //
    private String searchKeyword;
    private String status;
}