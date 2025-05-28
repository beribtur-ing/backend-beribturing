package ing.beribtur.storejpa.aggregate.beribturing.repository;

import ing.beribtur.storejpa.aggregate.beribturing.jpo.SubscriptionPlanJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlanJpo, Long> {
    //
    Optional<SubscriptionPlanJpo> findByName(String name);
}
