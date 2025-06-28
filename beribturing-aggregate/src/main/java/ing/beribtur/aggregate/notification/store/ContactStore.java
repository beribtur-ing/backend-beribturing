package ing.beribtur.aggregate.notification.store;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.notification.entity.Contact;

import java.util.List;

public interface ContactStore {
    //
    void create(Contact contact);
    void createAll(List<Contact> contacts);
    Contact retrieve(String id);
    List<Contact> retrieveAll(List<String> contactIds);
    List<Contact> retrieveList(Offset offset);
    void update(Contact contact);
    void delete(Contact contact);
    void delete(String id);
    boolean exists(String id);

    Contact retrieveByUserId(String userId);
    boolean existsByUserId(String userId);
    List<Contact> retrieveByEmailSupport();
    List<Contact> retrieveBySmsSupport();
    List<Contact> retrieveByPushSupport();
}