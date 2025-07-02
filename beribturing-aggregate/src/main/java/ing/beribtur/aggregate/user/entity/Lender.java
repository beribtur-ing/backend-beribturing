package ing.beribtur.aggregate.user.entity;


import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.util.JsonUtil;
import ing.beribtur.aggregate.item.entity.Product;
import ing.beribtur.aggregate.user.entity.sdo.LenderCdo;
import ing.beribtur.aggregate.user.entity.vo.LenderType;
import ing.beribtur.aggregate.user.entity.vo.Profile;
import ing.beribtur.aggregate.user.entity.vo.LenderNotificationPreferences;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

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
    private long productSequence;
    private LenderNotificationPreferences notificationPreferences;

    // Domain relationships
    private transient List<Product> listedItems;

    public Lender(LenderCdo lenderCdo) {
        //
        super(lenderCdo.genId());
        BeanUtils.copyProperties(lenderCdo, this);
        this.productSequence = 1L;
    }

    public static String genId(String accountId) {
        return accountId;
    }

    @Override
    protected void modifyAttributes(NameValueList nameValues) {
        for (NameValue nameValue : nameValues.list()) {
            String value = nameValue.getValue();
            switch (nameValue.getName().trim()) {
                case "name" -> this.name = value;
                case "phoneNumber" -> this.phoneNumber = value;
                case "lenderType" -> this.lenderType = LenderType.valueOf(value);
                case "active" -> this.active = Boolean.parseBoolean(value);
                case "profile" -> this.profile = JsonUtil.fromJson(value, Profile.class);
                case "productSequence" -> this.productSequence = Long.parseLong(value);
                case "notificationPreferences" -> this.notificationPreferences = JsonUtil.fromJson(value, LenderNotificationPreferences.class);
                default -> throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }
}
