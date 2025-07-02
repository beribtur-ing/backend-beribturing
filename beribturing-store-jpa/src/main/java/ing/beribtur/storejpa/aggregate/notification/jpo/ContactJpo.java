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

import java.util.ArrayList;
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

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FcmTokenJpo> fcmTokens = new ArrayList<>();

    public ContactJpo(Contact contact) {
        //
        super(contact);
        this.userId = contact.getUserId();
        this.email = contact.getEmail();
        this.phoneNumber = contact.getPhoneNumber();
    }

    public Contact toDomain() {
        //
        Contact contact = new Contact();
        BeanUtils.copyProperties(this, contact);

        // Convert JSON back to List<String>
        if (this.fcmTokens != null) {
            List<String> fcmTokens = new ArrayList<>();
            for (FcmTokenJpo fcmTokenJpo : this.fcmTokens) {
                fcmTokens.add(fcmTokenJpo.getToken());
            }
            contact.setFcmTokens(fcmTokens);
        }

        return contact;
    }

    public static List<Contact> toDomains(List<ContactJpo> jpos) {
        //
        return jpos.stream().map(ContactJpo::toDomain).toList();
    }
}
