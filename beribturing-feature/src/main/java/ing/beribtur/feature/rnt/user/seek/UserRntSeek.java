package ing.beribtur.feature.rnt.user.seek;

import ing.beribtur.accent.context.SpaceContext;
import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.logic.AccountLogic;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.entity.vo.LendeeNotificationPreferences;
import ing.beribtur.aggregate.user.entity.vo.Profile;
import ing.beribtur.aggregate.user.entity.vo.LendeePrivacySettings;
import ing.beribtur.aggregate.user.entity.vo.LendeeSecuritySettings;
import ing.beribtur.aggregate.user.entity.vo.LendeeAppearanceSettings;
import ing.beribtur.aggregate.user.logic.LendeeLogic;
import ing.beribtur.feature.shared.sdo.UserMeRdo;
import ing.beribtur.feature.shared.sdo.LendeePrivacySettingsRdo;
import ing.beribtur.feature.shared.sdo.LendeeSecuritySettingsRdo;
import ing.beribtur.feature.shared.sdo.LendeeAppearanceSettingsRdo;
import ing.beribtur.feature.shared.sdo.LendeeAllSettingsRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static ing.beribtur.aggregate.account.entity.vo.Role.ROLE_RENTER;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRntSeek {
    private final AccountLogic accountLogic;
    private final LendeeLogic lendeeLogic;

    public UserMeRdo userMe() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String phoneNumber = securityContext.getAuthentication().getName();
        Account account = accountLogic.findByPhoneNumberAndRole(phoneNumber, ROLE_RENTER.name());
        Lendee lendee = lendeeLogic.retrieve(account.getId());
        
        return UserMeRdo.builder()
                .avatarUrl(Optional.ofNullable(lendee.getProfile()).map(Profile::getAvatarUrl).orElse(null))
                .address(Optional.ofNullable(lendee.getProfile()).map(Profile::getAddress).orElse(null))
                .location(Optional.ofNullable(lendee.getProfile()).map(Profile::getLocation).orElse(null))
                .gender(Optional.ofNullable(lendee.getProfile()).map(Profile::getGender).orElse(null))
                .phoneNumber(account.getPhoneNumber())
                .email(account.getEmail())
                .role(account.getRole())
                .name(lendee.getName())
                .build();
    }

    public LendeeAllSettingsRdo getAllSettings() {
        String username = SpaceContext.get().getUsername();
        Lendee lendee = lendeeLogic.findByPhoneNumber(username);
        
        LendeePrivacySettings privacySettings = lendee.getPrivacySettings();
        if (privacySettings == null) {
            privacySettings = LendeePrivacySettings.getDefaultSettings();
        }
        
        LendeeSecuritySettings securitySettings = lendee.getSecuritySettings();
        if (securitySettings == null) {
            securitySettings = LendeeSecuritySettings.getDefaultSettings();
        }
        
        LendeeAppearanceSettings appearanceSettings = lendee.getAppearanceSettings();
        if (appearanceSettings == null) {
            appearanceSettings = LendeeAppearanceSettings.getDefaultSettings();
        }
        
        LendeeNotificationPreferences notificationPreferences = lendee.getNotificationPreferences();
        if (notificationPreferences == null) {
            notificationPreferences = LendeeNotificationPreferences.createDefault();
        }
        
        return new LendeeAllSettingsRdo(
            LendeePrivacySettingsRdo.from(privacySettings),
            LendeeSecuritySettingsRdo.from(securitySettings),
            LendeeAppearanceSettingsRdo.from(appearanceSettings),
            notificationPreferences
        );
    }
} 