package ing.beribtur.facade.api.feature.own.user.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.feature.shared.sdo.UserMeRdo;

public interface UserOwnSeekFacade {
    QueryResponse<UserMeRdo> userMe();
}
