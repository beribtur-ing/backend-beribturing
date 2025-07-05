package ing.beribtur.storejpa.aggregate.user;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.user.entity.Lender;
import ing.beribtur.aggregate.user.entity.vo.LenderType;
import ing.beribtur.aggregate.user.store.LenderStore;
import ing.beribtur.storejpa.aggregate.user.jpo.LenderJpo;
import ing.beribtur.storejpa.aggregate.user.repository.LenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
public class LenderJpaStore implements LenderStore {
    //
    private final LenderRepository lenderRepository;

    @Override
    public void create(Lender lender) {
        //
        LenderJpo lenderJpo = new LenderJpo(lender);
        lenderRepository.save(lenderJpo);
        lender.setId(lenderJpo.getId());
    }

    @Override
    public void createAll(List<Lender> lenders) {
        //
        lenderRepository.saveAll(lenders.stream().map(LenderJpo::new).collect(Collectors.toList()));
    }

    @Override
    public Lender retrieve(String id) {
        //
        return lenderRepository.findById(id)
                .map(LenderJpo::toDomain)
                .orElse(null);
    }

    @Override
    public List<Lender> retrieveAll(List<String> ids) {
        //
        Iterable<LenderJpo> allById = lenderRepository.findAllById(ids);
        return LenderJpo.toDomains(StreamSupport.stream(allById.spliterator(), false).collect(Collectors.toList()));
    }

    @Override
    public List<Lender> retrieveList(Offset offset) {
        //
        Pageable pageable = PageRequest.of(offset.page(), offset.limit());
        return lenderRepository.findAll(pageable).map(LenderJpo::toDomain).toList();
    }

    @Override
    public void update(Lender lender) {
        //
        lenderRepository.save(new LenderJpo(lender));
    }

    @Override
    public void delete(Lender lender) {
        //
        lenderRepository.delete(new LenderJpo(lender));
    }

    @Override
    public void delete(String id) {
        //
        lenderRepository.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        //
        return lenderRepository.existsById(id);
    }

    @Override
    public Lender findByPhoneNumber(String phoneNumber) {
        //
        LenderJpo lenderJpo = lenderRepository.findByPhoneNumber(phoneNumber);
        return lenderJpo != null ? lenderJpo.toDomain() : null;
    }

    public List<Lender> findByEmail(String email) {
        return LenderJpo.toDomains(lenderRepository.findByEmail(email));
    }

//    public List<Lender> findByIsActive(boolean isActive) {
//        return LenderJpo.toDomains(lenderRepository.findByIsActive(isActive));
//    }

    public List<Lender> findByLanderType(LenderType landerType) {
        return LenderJpo.toDomains(lenderRepository.findByLenderType(landerType.name()));
    }

    @Override
    public Page<Lender> findDisabledLenders(Pageable pageable) {
        Page<LenderJpo> lenderJpos = lenderRepository.findByActive(false, pageable);
        return new PageImpl<>(
                LenderJpo.toDomains(lenderJpos.getContent()),
                pageable,
                lenderJpos.getTotalElements()
        );
    }

    public long countActiveUsers() {
        //
        return lenderRepository.countByActive(true);
    }
}

