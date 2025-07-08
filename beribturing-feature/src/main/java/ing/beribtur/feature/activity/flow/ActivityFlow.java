package ing.beribtur.feature.activity.flow;

import ing.beribtur.aggregate.activity.entity.Activity;
import ing.beribtur.aggregate.activity.entity.sdo.ActivityCdo;
import ing.beribtur.aggregate.activity.logic.ActivityLogic;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ActivityFlow {
    //
    private final ActivityLogic activityLogic;

    public ActivityFlow(ActivityLogic activityLogic) {
        //
        this.activityLogic = activityLogic;
    }

    public String registerActivity(ActivityCdo cdo) {
        //
        if (cdo.getTimestamp() == null) {
            cdo.setTimestamp(LocalDateTime.now());
        }
        Activity activity = new Activity(cdo);
        this.activityLogic.create(activity);
        return activity.getId();
    }

    public void modifyActivity(String activityId, Activity activity) {
        //
        Activity existingActivity = this.activityLogic.findActivity(activityId);
        activity.setId(existingActivity.getId());
        this.activityLogic.modifyActivity(activity);
    }

    public void removeActivity(String activityId) {
        //
        this.activityLogic.removeActivity(activityId);
    }
}
