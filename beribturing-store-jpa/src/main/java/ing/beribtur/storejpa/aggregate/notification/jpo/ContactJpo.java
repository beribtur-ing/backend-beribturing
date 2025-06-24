package ing.beribtur.storejpa.aggregate.notification.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.notification.entity.Contact;
import ing.beribtur.accent.util.JsonUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    
    @Column(columnDefinition = "TEXT")
    private String fcmTokens;

    public ContactJpo(Contact contact) {
        //
        super(contact);
        this.userId = contact.getUserId();
        this.email = contact.getEmail();
        this.phoneNumber = contact.getPhoneNumber();
        
        // Convert List<String> to JSON
        if (contact.getFcmTokens() != null) {
            this.fcmTokens = JsonUtil.toJson(contact.getFcmTokens());
        }
    }

    public Contact toDomain() {
        //
        Contact contact = new Contact();
        contact.setId(this.getId());
        contact.setEntityVersion(this.getEntityVersion());
        contact.setRegisteredBy(this.getRegisteredBy());
        contact.setRegisteredOn(this.getRegisteredOn());
        contact.setModifiedBy(this.getModifiedBy());
        contact.setModifiedOn(this.getModifiedOn());
        
        contact.setUserId(this.userId);
        contact.setEmail(this.email);
        contact.setPhoneNumber(this.phoneNumber);
        
        // Convert JSON back to List<String>
        if (this.fcmTokens != null) {
            contact.setFcmTokens(JsonUtil.fromJsonToList(this.fcmTokens, String.class));
        }
        
        return contact;
    }

    public static List<Contact> toDomains(List<ContactJpo> jpos) {
        //
        return jpos.stream().map(ContactJpo::toDomain).toList();
    }
}