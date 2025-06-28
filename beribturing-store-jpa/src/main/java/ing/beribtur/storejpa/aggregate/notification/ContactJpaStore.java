package ing.beribtur.storejpa.aggregate.notification;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.notification.entity.Contact;
import ing.beribtur.aggregate.notification.store.ContactStore;
import ing.beribtur.storejpa.aggregate.notification.jpo.ContactJpo;
import ing.beribtur.storejpa.aggregate.notification.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
public class ContactJpaStore implements ContactStore {
    //
    private final ContactRepository contactRepository;

    @Override
    public void create(Contact contact) {
        //
        ContactJpo contactJpo = new ContactJpo(contact);
        contactRepository.save(contactJpo);
    }

    @Override
    public void createAll(List<Contact> contacts) {
        //
        contactRepository.saveAll(contacts.stream().map(ContactJpo::new).collect(Collectors.toList()));
    }

    @Override
    public Contact retrieve(String id) {
        //
        return contactRepository.findById(id)
            .map(ContactJpo::toDomain)
            .orElse(null);
    }

    @Override
    public List<Contact> retrieveAll(List<String> contactIds) {
        //
        Iterable<ContactJpo> allById = contactRepository.findAllById(contactIds);
        return ContactJpo.toDomains(StreamSupport.stream(allById.spliterator(), false).collect(Collectors.toList()));
    }

    @Override
    public List<Contact> retrieveList(Offset offset) {
        //
        Pageable pageable = PageRequest.of(offset.page(), offset.limit());
        return contactRepository.findAll(pageable).map(ContactJpo::toDomain).toList();
    }

    @Override
    public void update(Contact contact) {
        //
        contactRepository.save(new ContactJpo(contact));
    }

    @Override
    public void delete(Contact contact) {
        //
        contactRepository.delete(new ContactJpo(contact));
    }

    @Override
    public void delete(String id) {
        //
        contactRepository.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        //
        return contactRepository.existsById(id);
    }

    @Override
    public Contact retrieveByUserId(String userId) {
        //
        return contactRepository.findByUserId(userId)
            .map(ContactJpo::toDomain)
            .orElse(null);
    }

    @Override
    public boolean existsByUserId(String userId) {
        //
        return contactRepository.existsByUserId(userId);
    }

    @Override
    public List<Contact> retrieveByEmailSupport() {
        //
        return contactRepository.findByEmailIsNotNull().stream()
            .map(ContactJpo::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<Contact> retrieveBySmsSupport() {
        //
        return contactRepository.findByPhoneNumberIsNotNull().stream()
            .map(ContactJpo::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<Contact> retrieveByPushSupport() {
        //
        return contactRepository.findByFcmTokensIsNotEmpty().stream()
            .map(ContactJpo::toDomain)
            .collect(Collectors.toList());
    }
}