package ing.beribtur.storejpa.aggregate.notification;

import ing.beribtur.aggregate.notification.entity.Contact;
import ing.beribtur.storejpa.aggregate.notification.jpo.ContactJpo;
import ing.beribtur.storejpa.aggregate.notification.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ContactJpaStore {
    //
    private final ContactRepository contactRepository;

    public void create(Contact contact) {
        ContactJpo contactJpo = new ContactJpo(contact);
        contactRepository.save(contactJpo);
        contact.setId(contactJpo.getId());
    }

    public Contact retrieve(String userId) {
        ContactJpo contactJpo = contactRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Contact not found for user: " + userId));
        return contactJpo.toDomain();
    }

    public void update(Contact contact) {
        ContactJpo existingJpo = contactRepository.findByUserId(contact.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Contact not found for user: " + contact.getUserId()));

        ContactJpo updatedJpo = new ContactJpo(contact);
        updatedJpo.setId(existingJpo.getId());
        updatedJpo.setEntityVersion(existingJpo.getEntityVersion());
        updatedJpo.setRegisteredBy(existingJpo.getRegisteredBy());
        updatedJpo.setRegisteredOn(existingJpo.getRegisteredOn());

        contactRepository.save(updatedJpo);
    }

    public void delete(String userId) {
        contactRepository.deleteByUserId(userId);
    }

    public boolean existsByUserId(String userId) {
        return contactRepository.findByUserId(userId).isPresent();
    }
}