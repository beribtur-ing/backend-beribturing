package ing.beribtur.facade.api.feature.own.user.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.user.entity.vo.LenderNotificationPreferences;
import ing.beribtur.facade.api.feature.own.user.query.UserMeOwnQuery;
import ing.beribtur.feature.shared.sdo.UserMeRdo;

public interface UserOwnSeekFacade {
    QueryResponse<UserMeRdo> userMe(UserMeOwnQuery query);
    QueryResponse<LenderNotificationPreferences>  getNotificationPreferences() ;
}