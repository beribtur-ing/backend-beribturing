package ing.beribtur.aggregate.notification.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.notification.entity.sdo.ContactCdo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
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

    public Contact(ContactCdo contactCdo) {
        //
        BeanUtils.copyProperties(contactCdo,this);
        this.fcmTokens = new ArrayList<>();
    }

    public boolean hasPushSupport() {
        //
        return fcmTokens != null && !fcmTokens.isEmpty();
    }

    public boolean hasEmailSupport() {
        //
        return email != null && !email.isBlank();
    }

    public boolean hasSmsSupport() {
        //
        return phoneNumber != null && !phoneNumber.isBlank();
    }

    public static Contact sample() {
        //
        Contact contact = new Contact();
        contact.setUserId("user123");
        contact.setEmail("user@example.com");
        contact.setPhoneNumber("+1234567890");
        contact.setFcmTokens(Arrays.asList("fcm_token_1", "fcm_token_2"));
        return contact;
    }

    public static Contact sampleWithEmail() {
        //
        Contact contact = new Contact();
        contact.setUserId("user456");
        contact.setEmail("contact@domain.com");
        contact.setPhoneNumber(null);
        contact.setFcmTokens(null);
        return contact;
    }

    public static Contact sampleWithSms() {
        //
        Contact contact = new Contact();
        contact.setUserId("user789");
        contact.setEmail(null);
        contact.setPhoneNumber("+9876543210");
        contact.setFcmTokens(null);
        return contact;
    }

    public static Contact sampleWithPush() {
        //
        Contact contact = new Contact();
        contact.setUserId("user101");
        contact.setEmail(null);
        contact.setPhoneNumber(null);
        contact.setFcmTokens(Arrays.asList("push_token_abc", "push_token_def"));
        return contact;
    }

    @Override
    protected void modifyAttributes(NameValueList nameValueList) {
        /*this.userId = nameValueList.getValueOfName("userId");
        this.email = nameValueList.getValueOfName("email");
        this.phoneNumber = nameValueList.getValueOfName("phoneNumber");
        this.fcmTokens = nameValueList.getValueOfName("fcmTokens");*/
    }
}
