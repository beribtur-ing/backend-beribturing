package ing.beribtur.storejpa.aggregate.notification.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.accent.util.Beans;
import ing.beribtur.aggregate.notification.entity.Contact;
import ing.beribtur.accent.util.JsonUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CONTACT")
public class ContactJpo extends DomainEntityJpo {

    @Column(nullable = false, unique = true)
    private String userId;

    private String email;

    private String phoneNumber;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(columnDefinition = "TEXT")
    private List<String> fcmTokens;

    public ContactJpo(Contact contact) {
        //
        super(contact);
        this.userId = contact.getUserId();
        this.email = contact.getEmail();
        this.phoneNumber = contact.getPhoneNumber();

        // Convert List<String> to JSON
        if (contact.getFcmTokens() != null) {
            this.fcmTokens = contact.getFcmTokens();
        }
    }

    public Contact toDomain() {
        //
        Contact contact = new Contact();
        BeanUtils.copyProperties(this, contact);

        // Convert JSON back to List<String>
        if (this.fcmTokens != null) {
            contact.setFcmTokens(this.fcmTokens);
        }

        return contact;
    }

    public static List<Contact> toDomains(List<ContactJpo> jpos) {
        //
        return jpos.stream().map(ContactJpo::toDomain).toList();
    }
}
