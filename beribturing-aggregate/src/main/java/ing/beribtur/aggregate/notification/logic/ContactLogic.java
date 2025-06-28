package ing.beribtur.aggregate.notification.logic;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.util.Entities;
import ing.beribtur.aggregate.notification.entity.Contact;
import ing.beribtur.aggregate.notification.entity.sdo.ContactCdo;
import ing.beribtur.aggregate.notification.store.ContactStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ContactLogic {
    //
    private final ContactStore contactStore;

    public String registerContact(ContactCdo contactCdo) {
        //
        Contact contact = new Contact(contactCdo);
        if (contactStore.exists(contact.getId())) {
            throw new IllegalArgumentException("contact already exists. " + contact.getId());
        }
        contactStore.create(contact);
        return contact.getId();
    }

    public List<String> registerContacts(List<ContactCdo> contactCdos) {
        //
        return contactCdos.stream().map(this::registerContact).collect(Collectors.toList());
    }

    public Contact findContact(String contactId) {
        //
        Contact contact = contactStore.retrieve(contactId);
        if (contact == null) {
            throw new NoSuchElementException("Contact id: " + contactId);
        }
        return contact;
    }

    public Contact findContactByUserId(String userId) {
        //
        Contact contact = contactStore.retrieveByUserId(userId);
        if (contact == null) {
            throw new NoSuchElementException("Contact not found for user id: " + userId);
        }
        return contact;
    }

    public List<Contact> findContacts(Offset offset) {
        //
        return contactStore.retrieveList(offset);
    }

    public List<Contact> findContactsWithEmailSupport() {
        //
        return contactStore.retrieveByEmailSupport();
    }

    public List<Contact> findContactsWithSmsSupport() {
        //
        return contactStore.retrieveBySmsSupport();
    }

    public List<Contact> findContactsWithPushSupport() {
        //
        return contactStore.retrieveByPushSupport();
    }

    public void modifyContact(String contactId, NameValueList nameValues) {
        //
        Contact contact = findContact(contactId);
        contact.modify(nameValues);
        contactStore.update(contact);
    }

    public void modifyContact(Contact contact) {
        //
        Contact oldContact = findContact(contact.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldContact, contact);
        if (!nameValues.list().isEmpty()) {
            modifyContact(contact.getId(), nameValues);
        }
    }

    public void removeContact(String contactId) {
        //
        Contact contact = findContact(contactId);
        contactStore.delete(contact);
    }

    public boolean existsContact(String contactId) {
        //
        return contactStore.exists(contactId);
    }

    public boolean existsContactByUserId(String userId) {
        //
        return contactStore.existsByUserId(userId);
    }

    public void addFcmToken(String contactId, String fcmToken) {
        //
        Contact contact = findContact(contactId);
        if (!contact.getFcmTokens().contains(fcmToken)) {
            contact.getFcmTokens().add(fcmToken);
            contactStore.update(contact);
        }
    }

    public void removeFcmToken(String contactId, String fcmToken) {
        //
        Contact contact = findContact(contactId);
        contact.getFcmTokens().remove(fcmToken);
        contactStore.update(contact);
    }
}