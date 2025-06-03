package ing.beribtur.facade.api.feature.adm.lender.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
@NoArgsConstructor
public class FindDisabledLendersAdmQuery {
    private int page = 0;
    private int size = 10;
    private String sort = "registeredOn,desc";

    public void validate() {
        //
        Assert.isTrue(page >= 0, "page must be greater than or equal to zero");
        Assert.isTrue(size >= 1, "size must be greater than or equal to one");
    }
}