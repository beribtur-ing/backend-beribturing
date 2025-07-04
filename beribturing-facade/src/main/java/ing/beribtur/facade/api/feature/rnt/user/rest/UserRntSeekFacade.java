package ing.beribtur.facade.api.feature.rnt.user.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.feature.shared.sdo.LendeeAllSettingsRdo;
import ing.beribtur.feature.shared.sdo.UserMeRdo;

public interface UserRntSeekFacade {
    QueryResponse<UserMeRdo> userMe();
    QueryResponse<LendeeAllSettingsRdo> getAllSettings();
}
