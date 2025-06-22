package ing.beribtur.storejpa.aggregate.payment;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.payment.entity.Discount;
import ing.beribtur.aggregate.payment.entity.vo.DiscountScope;
import ing.beribtur.aggregate.payment.entity.vo.DiscountType;
import ing.beribtur.aggregate.payment.store.DiscountStore;
import ing.beribtur.storejpa.aggregate.payment.jpo.DiscountJpo;
import ing.beribtur.storejpa.aggregate.payment.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class DiscountJpaStore implements DiscountStore {
    //
    private final DiscountRepository discountRepository;

    @Override
    public void create(Discount discount) {
        DiscountJpo discountJpo = new DiscountJpo(discount);

        discountRepository.save(discountJpo);
        discount.setId(discountJpo.getId());
    }

    @Override
    public Discount retrieve(String id) {
        DiscountJpo discountJpo = discountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Discount not found: " + id));
        return discountJpo.toDomain();
    }

    @Override
    public List<Discount> retrieveAll(List<String> ids) {
        List<DiscountJpo> discountJpos = discountRepository.findAllById(ids);
        return discountJpos.stream()
                .map(DiscountJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Discount discount) {
        DiscountJpo discountJpo = discountRepository.findById(discount.getId())
                .orElseThrow(() -> new IllegalArgumentException("Discount not found: " + discount.getId()));

        // Update the JPO with the domain entity's values
        DiscountJpo updatedJpo = new DiscountJpo(discount);
        updatedJpo.setEntityVersion(discountJpo.getEntityVersion());
        updatedJpo.setRegisteredBy(discountJpo.getRegisteredBy());
        updatedJpo.setRegisteredOn(discountJpo.getRegisteredOn());

        discountRepository.save(updatedJpo);
    }

    @Override
    public void delete(String id) {
        discountRepository.deleteById(id);
    }

    // Additional methods for specific queries
    public List<Discount> findByName(String name) {
        return DiscountJpo.toDomains(discountRepository.findByName(name));
    }

    public List<Discount> findByType(DiscountType type) {
        return DiscountJpo.toDomains(discountRepository.findByType(type.name()));
    }

    public List<Discount> findByScope(DiscountScope scope) {
        return DiscountJpo.toDomains(discountRepository.findByScope(scope.name()));
    }

    public List<Discount> findByTargetId(String targetId) {
        return DiscountJpo.toDomains(discountRepository.findByTargetId(targetId));
    }

    public List<Discount> findByActive(boolean active) {
        return DiscountJpo.toDomains(discountRepository.findByActive(active));
    }

    public List<Discount> findCurrentlyActive() {
        LocalDateTime now = LocalDateTime.now();
        return DiscountJpo.toDomains(
                discountRepository.findByStartDateBeforeAndEndDateAfterAndActive(now, now, true)
        );
    }

    @Override
    public List<Discount> retrieveList(Offset offset) {
        Pageable pageable = PageRequest.of(offset.page(), offset.limit());

        return discountRepository.findAll(pageable).map(DiscountJpo::toDomain).toList();
    }

    @Override
    public boolean exists(String id) {
        return discountRepository.existsById(id);
    }

    @Override
    public List<Discount> retrieveByActive(boolean active) {
        return discountRepository.findByActive(active).stream().map(DiscountJpo::toDomain).toList();
    }

    @Override
    public List<Discount> retrieveByScope(DiscountScope scope) {
        return discountRepository.findByScope(scope.name()).stream().map(DiscountJpo::toDomain).toList();
    }

    @Override
    public List<Discount> retrieveByTargetId(String targetId) {
        return discountRepository.findByTargetId(targetId).stream().map(DiscountJpo::toDomain).toList();
    }

    @Override
    public List<Discount> retrieveByEndDateBefore(LocalDateTime dateTime) {
        return discountRepository.findByEndDateBefore(dateTime).stream()
                .map(DiscountJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Discount> retrieveValidDiscounts(LocalDateTime now) {
        return discountRepository.findByStartDateBeforeAndEndDateAfterAndActive(now, now, true)
                .stream()
                .map(DiscountJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Discount> retrieveApplicableDiscounts(String targetId, DiscountScope scope, LocalDateTime now) {
        return discountRepository.findByTargetIdAndScopeAndStartDateBeforeAndEndDateAfterAndActive(
                targetId, scope.name(), now, now, true
        ).stream().map(DiscountJpo::toDomain).collect(Collectors.toList());
    }
}
