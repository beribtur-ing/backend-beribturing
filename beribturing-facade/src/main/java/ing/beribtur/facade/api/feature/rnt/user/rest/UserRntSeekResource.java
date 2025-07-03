package ing.beribtur.facade.api.feature.rnt.user.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.user.entity.vo.LendeeNotificationPreferences;
import ing.beribtur.feature.rnt.user.seek.UserRntSeek;
import ing.beribtur.feature.shared.sdo.UserMeRdo;
import ing.beribtur.feature.shared.sdo.LendeePrivacySettingsRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feature/renter/user")
@RequiredArgsConstructor
public class UserRntSeekResource implements UserRntSeekFacade {
    private final UserRntSeek userRntSeek;

    @Override
    @PostMapping("/me/query")
    public QueryResponse<UserMeRdo> userMe() {
        UserMeRdo rdo = userRntSeek.userMe();
        return new QueryResponse<>(rdo);
    }

    @Override
    @PostMapping("/find-privacy-settings/query")
    public LendeePrivacySettingsRdo getPrivacySettings() {
        return userRntSeek.getPrivacySettings();
    }

    @Override
    @PostMapping("/notification-preferences")
    public LendeeNotificationPreferences getNotificationPreferences() {
        return userRntSeek.getNotificationPreferences();
    }
} 