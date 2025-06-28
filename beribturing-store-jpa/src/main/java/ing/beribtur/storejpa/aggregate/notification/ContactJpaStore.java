package ing.beribtur.storejpa.aggregate.notification;

import ing.beribtur.aggregate.notification.entity.Contact;
import ing.beribtur.aggregate.notification.store.ContactStore;
import ing.beribtur.storejpa.aggregate.notification.jpo.ContactJpo;
import ing.beribtur.storejpa.aggregate.notification.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ContactJpaStore implements ContactStore {
    //
    private final ContactRepository contactRepository;

    @Override
    public void createContact(Contact contact) {
        ContactJpo contactJpo = new ContactJpo(contact);
        contactRepository.save(contactJpo);
        contact.setId(contactJpo.getId());
    }

    @Override
    public Contact retrieveContact(String userId) {
        return contactRepository.findByUserId(userId)
                .map(ContactJpo::toDomain)
                .orElse(null);
    }

    @Override
    public void updateContact(Contact contact) {
        ContactJpo existingJpo = contactRepository.findByUserId(contact.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Contact not found for user: " + contact.getUserId()));

        ContactJpo updatedJpo = new ContactJpo(contact);
        BeanUtils.copyProperties(existingJpo, updatedJpo);

        contactRepository.save(updatedJpo);
    }

    @Override
    public void deleteContact(String userId) {
        contactRepository.deleteByUserId(userId);
    }

    public boolean existsByUserId(String userId) {
        return contactRepository.findByUserId(userId).isPresent();
    }
}
