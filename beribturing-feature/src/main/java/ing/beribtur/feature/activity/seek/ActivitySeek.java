package ing.beribtur.feature.activity.seek;

import ing.beribtur.aggregate.activity.entity.Activity;
import ing.beribtur.aggregate.activity.entity.vo.ActivityType;
import ing.beribtur.aggregate.activity.logic.ActivityLogic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivitySeek {
    //
    private final ActivityLogic activityLogic;

    public ActivitySeek(ActivityLogic activityLogic) {
        //
        this.activityLogic = activityLogic;
    }

    public Activity findActivity(String activityId) {
        //
        return this.activityLogic.findActivity(activityId);
    }

    public List<Activity> findActivitiesByUserId(String userId) {
        //
        return this.activityLogic.findByUserId(userId);
    }

    public List<Activity> findActivitiesByUserIdAndType(String userId, ActivityType type) {
        //
        return this.activityLogic.findByUserIdAndType(userId, type);
    }

    public List<Activity> findActivitiesByRelatedEntityId(String relatedEntityId) {
        //
        return this.activityLogic.findByRelatedEntityId(relatedEntityId);
    }
}
