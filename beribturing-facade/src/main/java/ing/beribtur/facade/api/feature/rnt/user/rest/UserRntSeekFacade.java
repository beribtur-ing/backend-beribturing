package ing.beribtur.facade.api.feature.rnt.user.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.user.entity.vo.LendeeNotificationPreferences;
import ing.beribtur.feature.shared.sdo.UserMeRdo;
import ing.beribtur.feature.shared.sdo.LendeePrivacySettingsRdo;

public interface UserRntSeekFacade {
    QueryResponse<UserMeRdo> userMe();
    LendeePrivacySettingsRdo getPrivacySettings();
    LendeeNotificationPreferences getNotificationPreferences();
} 