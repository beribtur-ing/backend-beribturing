package ing.beribtur.facade.api.feature.adm.user.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.feature.shared.sdo.UserMeRdo;

public interface UserAdmSeekFacade {
    QueryResponse<UserMeRdo> userMe();
}
