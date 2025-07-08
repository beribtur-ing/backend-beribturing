package ing.beribtur.aggregate.activity.logic;

import ing.beribtur.aggregate.activity.entity.Activity;
import ing.beribtur.aggregate.activity.entity.vo.ActivityType;
import ing.beribtur.aggregate.activity.store.ActivityStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class ActivityLogic {
    //
    private final ActivityStore activityStore;

    public Activity findActivity(String activityId) {
        Activity activity = activityStore.retrieve(activityId);
        if (activity == null) {
            throw new NoSuchElementException("Activity id: " + activityId);
        }
        return activity;
    }

    public List<Activity> findByUserId(String userId) {
        return activityStore.findByUserId(userId);
    }

    public List<Activity> findByUserIdAndType(String userId, ActivityType type) {
        return activityStore.findByUserIdAndType(userId, type);
    }

    public List<Activity> findByRelatedEntityId(String relatedEntityId) {
        return activityStore.findByRelatedEntityId(relatedEntityId);
    }

    public void create(Activity activity) {
        activityStore.create(activity);
    }

    public void modifyActivity(Activity activity) {
        activityStore.update(activity);
    }

    public void removeActivity(String activityId) {
        activityStore.delete(activityId);
    }
}