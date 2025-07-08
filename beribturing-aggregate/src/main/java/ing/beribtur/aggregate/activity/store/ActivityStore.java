package ing.beribtur.aggregate.activity.store;

import ing.beribtur.aggregate.activity.entity.Activity;
import ing.beribtur.aggregate.activity.entity.vo.ActivityType;

import java.util.List;

public interface ActivityStore {
    //
    void create(Activity activity);
    Activity retrieve(String id);
    List<Activity> retrieveAll(List<String> ids);
    void update(Activity activity);
    void delete(String id);
    List<Activity> findByUserId(String userId);
    List<Activity> findByUserIdAndType(String userId, ActivityType type);
    List<Activity> findByRelatedEntityId(String relatedEntityId);
}