package ing.beribtur.facade.api.feature.own.activity.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.activity.entity.Activity;
import ing.beribtur.facade.api.feature.own.activity.query.FindLatestActivitiesQuery;
import java.util.List;

public interface ActivitySeekFacade {
    //
    QueryResponse<List<Activity>> findLatestActivities(FindLatestActivitiesQuery query);
}
