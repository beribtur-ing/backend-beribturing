package ing.beribtur.aggregate.user.entity;


import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.item.entity.Product;
import ing.beribtur.aggregate.user.entity.vo.LenderType;
import ing.beribtur.aggregate.user.entity.vo.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Lender extends DomainEntity {
    //
    private String name;
    private String phoneNumber;    //can be used as username
    private LenderType lenderType;
    private boolean active;
    private Profile profile;

    // Domain relationships
    private transient List<Product> listedItems;

    public static String genId(String accountId) {
        return accountId;
    }

    @Override
    protected void modifyAttributes(NameValueList var1) {

    }
}
