package ing.beribtur.storejpa.aggregate.activity.repository;

import ing.beribtur.storejpa.aggregate.activity.jpo.ActivityJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<ActivityJpo, String> {
    List<ActivityJpo> findByUserId(String userId);
    
    List<ActivityJpo> findByUserIdAndType(String userId, String type);
    
    List<ActivityJpo> findByRelatedEntityId(String relatedEntityId);
}