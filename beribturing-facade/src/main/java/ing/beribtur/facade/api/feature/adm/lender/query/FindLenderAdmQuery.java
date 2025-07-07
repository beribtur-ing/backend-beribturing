package ing.beribtur.facade.api.feature.adm.lender.query;

import ing.beribtur.accent.message.QueryRequest;
import ing.beribtur.aggregate.user.entity.Lendee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
@NoArgsConstructor
public class FindLenderAdmQuery extends QueryRequest<Lendee> {
    //
    private String lenderId;

    public void validate() {
        //
        Assert.hasText(lenderId, "lenderId must not be empty");
    }
}
