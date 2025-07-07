package ing.beribtur.facade.api.feature.adm.lendee.query;

import ing.beribtur.accent.message.QueryRequest;
import ing.beribtur.aggregate.user.entity.Lendee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
@NoArgsConstructor
public class FindLendeeAdmQuery extends QueryRequest<Lendee> {
    //
    private String lendeeId;

    public void validate() {
        //
        Assert.hasText(lendeeId, "lendeeId must not be empty");
    }
}
