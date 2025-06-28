package ing.beribtur.aggregate.notification.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValueList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Contact extends DomainEntity {
    //
    private String userId;
    private String email;
    private String phoneNumber;
    private List<String> fcmTokens; // multiple devices

    public boolean hasPushSupport() {
        return fcmTokens != null && !fcmTokens.isEmpty();
    }

    public boolean hasEmailSupport() {
        return email != null && !email.isBlank();
    }

    public boolean hasSmsSupport() {
        return phoneNumber != null && !phoneNumber.isBlank();
    }

    @Override
    protected void modifyAttributes(NameValueList nameValueList) {
        this.userId = nameValueList.getValueOfName("userId");
        this.email = nameValueList.getValueOfName("email");
        this.phoneNumber = nameValueList.getValueOfName("phoneNumber");
        this.fcmTokens = nameValueList.getValueOfName("fcmTokens");
    }
}
