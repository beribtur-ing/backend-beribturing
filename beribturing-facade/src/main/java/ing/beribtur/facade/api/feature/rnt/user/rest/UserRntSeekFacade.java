package ing.beribtur.facade.api.feature.rnt.user.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.facade.api.feature.rnt.user.query.UserMeRntQuery;
import ing.beribtur.feature.shared.sdo.UserMeRdo;

public interface UserRntSeekFacade {
    QueryResponse<UserMeRdo> userMe(UserMeRntQuery query);
} 