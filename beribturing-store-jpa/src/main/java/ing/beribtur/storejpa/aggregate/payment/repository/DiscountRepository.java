package ing.beribtur.storejpa.aggregate.payment.repository;

import ing.beribtur.storejpa.aggregate.payment.jpo.DiscountJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DiscountRepository extends JpaRepository<DiscountJpo, String> {
    List<DiscountJpo> findByName(String name);
    List<DiscountJpo> findByType(String type);
    List<DiscountJpo> findByScope(String scope);
    List<DiscountJpo> findByTargetId(String targetId);
    List<DiscountJpo> findByActive(boolean active);
    List<DiscountJpo> findByStartDateBefore(LocalDateTime date);
    List<DiscountJpo> findByEndDateAfter(LocalDateTime date);
    List<DiscountJpo> findByStartDateBeforeAndEndDateAfterAndActive(LocalDateTime now, LocalDateTime now2, boolean active);
    List<DiscountJpo> findByEndDateBefore(LocalDateTime dateTime);
    List<DiscountJpo> findByTargetIdAndScopeAndStartDateBeforeAndEndDateAfterAndActive(String targetId, String name, LocalDateTime now, LocalDateTime now1, boolean b);
}