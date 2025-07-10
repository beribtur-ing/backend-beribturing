package ing.beribtur.feature.activity.seek;

import ing.beribtur.aggregate.activity.entity.Activity;
import ing.beribtur.aggregate.activity.entity.vo.ActivityType;
import ing.beribtur.aggregate.activity.logic.ActivityLogic;
import ing.beribtur.aggregate.user.entity.Lender;
import ing.beribtur.aggregate.user.logic.LenderLogic;
import ing.beribtur.config.security.UserDetailsLogic;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivitySeek {
    //
    private final ActivityLogic activityLogic;
    private final LenderLogic lenderLogic;

    public ActivitySeek(ActivityLogic activityLogic, LenderLogic lenderLogic) {
        //
        this.activityLogic = activityLogic;
        this.lenderLogic = lenderLogic;
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

    public List<Activity> findLatestActivities(Integer limit) {
        //
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        Lender lender = lenderLogic.findByPhoneNumber(username);

        return this.activityLogic.findLatestActivitiesByUserId(lender.getId(), limit);
    }
}
