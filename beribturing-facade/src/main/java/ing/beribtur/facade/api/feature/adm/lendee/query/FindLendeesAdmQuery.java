package ing.beribtur.facade.api.feature.adm.lendee.query;

import ing.beribtur.accent.message.OffsetQueryRequest;
import ing.beribtur.aggregate.user.entity.Lendee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FindLendeesAdmQuery extends OffsetQueryRequest<List<Lendee>> {
    //
    private String searchKeyword;
    private String status;
}