package ing.beribtur.storejpa.aggregate.user;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.store.LendeeStore;
import ing.beribtur.storejpa.aggregate.user.jpo.LendeeJpo;
import ing.beribtur.storejpa.aggregate.user.repository.LendeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
public class LendeeJpaStore implements LendeeStore {
    //
    private final LendeeRepository lendeeRepository;

    @Override
    public void create(Lendee lendee) {
        //
        LendeeJpo lendeeJpo = new LendeeJpo(lendee);
        lendeeRepository.save(lendeeJpo);
        lendee.setId(lendeeJpo.getId());
    }

    @Override
    public void createAll(List<Lendee> lendees) {
        //
        lendeeRepository.saveAll(lendees.stream().map(LendeeJpo::new).collect(Collectors.toList()));
    }

    @Override
    public Lendee retrieve(String id) {
        //
        return lendeeRepository.findById(id)
                .map(LendeeJpo::toDomain)
                .orElse(null);
    }

    @Override
    public List<Lendee> retrieveAll(List<String> ids) {
        //
        Iterable<LendeeJpo> allById = lendeeRepository.findAllById(ids);
        return LendeeJpo.toDomains(StreamSupport.stream(allById.spliterator(), false).collect(Collectors.toList()));
    }

    @Override
    public List<Lendee> retrieveList(Offset offset) {
        //
        Pageable pageable = PageRequest.of(offset.page(), offset.limit());
        return lendeeRepository.findAll(pageable).map(LendeeJpo::toDomain).toList();
    }

    @Override
    public void update(Lendee lendee) {
        //
        lendeeRepository.save(new LendeeJpo(lendee));
    }

    @Override
    public void delete(Lendee lendee) {
        //
        lendeeRepository.delete(new LendeeJpo(lendee));
    }

    @Override
    public void delete(String id) {
        //
        lendeeRepository.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        //
        return lendeeRepository.existsById(id);
    }

    @Override
    public Lendee findByPhoneNumber(String phoneNumber) {
        //
        LendeeJpo lendeeJpo = lendeeRepository.findByPhoneNumber(phoneNumber);
        return lendeeJpo != null ? lendeeJpo.toDomain() : null;
    }

    public List<Lendee> findByEmail(String email) {
        return LendeeJpo.toDomains(lendeeRepository.findByEmail(email));
    }

    public List<Lendee> findByIsActive(boolean isActive) {
        return LendeeJpo.toDomains(lendeeRepository.findByActive(isActive));
    }
}

