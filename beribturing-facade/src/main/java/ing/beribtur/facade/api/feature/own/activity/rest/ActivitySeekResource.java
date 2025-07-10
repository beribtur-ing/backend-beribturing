package ing.beribtur.facade.api.feature.own.activity.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.activity.entity.Activity;
import ing.beribtur.facade.api.feature.own.activity.query.FindLatestActivitiesQuery;
import ing.beribtur.feature.activity.seek.ActivitySeek;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/feature/owner/activity")
@RequiredArgsConstructor
public class ActivitySeekResource implements ActivitySeekFacade {
    //
    private final ActivitySeek activitySeek;


    @Override
    @PostMapping("/find-latest-activities/query")
    public QueryResponse<List<Activity>> findLatestActivities(@RequestBody FindLatestActivitiesQuery query) {
        //
        Integer limit = query.getLimit();
        List<Activity> activities = this.activitySeek.findLatestActivities(limit);
        return new QueryResponse<>(activities);
    }
}
