package ing.beribtur.aggregate.notification.store;

import ing.beribtur.aggregate.notification.entity.Contact;

public interface ContactStore {
    //
    void createContact(Contact contact);
    Contact retrieveContact(String userId);
    void updateContact(Contact contact);
    void deleteContact(String userId);
}
