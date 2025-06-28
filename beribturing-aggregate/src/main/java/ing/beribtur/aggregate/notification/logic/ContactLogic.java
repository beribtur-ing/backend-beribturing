package ing.beribtur.aggregate.notification.logic;

import ing.beribtur.aggregate.notification.entity.Contact;
import ing.beribtur.aggregate.notification.store.ContactStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ContactLogic {
    //
    private final ContactStore contactStore;

    public String createContact(String userId, String email, String phoneNumber, List<String> fcmTokens) {
        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setUserId(userId);
        contact.setEmail(email);
        contact.setPhoneNumber(phoneNumber);
        contact.setFcmTokens(fcmTokens);

        contactStore.createContact(contact);
        return contact.getId();
    }

    public Contact findContactByUserId(String userId) {
        return contactStore.retrieveContact(userId);
    }

    public void updateContact(Contact contact) {
        contactStore.updateContact(contact);
    }

    public void addFcmToken(String userId, String fcmToken) {
        Contact contact = contactStore.retrieveContact(userId);
        if (contact != null) {
            List<String> tokens = contact.getFcmTokens();
            if (tokens != null && !tokens.contains(fcmToken)) {
                tokens.add(fcmToken);
                contact.setFcmTokens(tokens);
                contactStore.updateContact(contact);
            }
        }
    }

    public void removeFcmToken(String userId, String fcmToken) {
        Contact contact = contactStore.retrieveContact(userId);
        if (contact != null) {
            List<String> tokens = contact.getFcmTokens();
            if (tokens != null && tokens.contains(fcmToken)) {
                tokens.remove(fcmToken);
                contact.setFcmTokens(tokens);
                contactStore.updateContact(contact);
            }
        }
    }

    public void updateEmail(String userId, String email) {
        Contact contact = contactStore.retrieveContact(userId);
        if (contact != null) {
            contact.setEmail(email);
            contactStore.updateContact(contact);
        }
    }

    public void updatePhoneNumber(String userId, String phoneNumber) {
        Contact contact = contactStore.retrieveContact(userId);
        if (contact != null) {
            contact.setPhoneNumber(phoneNumber);
            contactStore.updateContact(contact);
        }
    }

    public void deleteContact(String userId) {
        contactStore.deleteContact(userId);
    }
}
