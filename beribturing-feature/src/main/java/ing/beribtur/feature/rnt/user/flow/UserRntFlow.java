package ing.beribtur.feature.rnt.user.flow;

import ing.beribtur.accent.context.SpaceContext;
import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.entity.vo.Role;
import ing.beribtur.aggregate.account.logic.AccountLogic;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.entity.vo.*;
import ing.beribtur.aggregate.user.logic.LendeeLogic;
import ing.beribtur.proxy.minio.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRntFlow {
    //
    private final LendeeLogic lendeeLogic;
    private final MinioService minioService;
    private final AccountLogic accountLogic;
    private final PasswordEncoder passwordEncoder;

    public String modifyProfile(String name, Gender gender, String email, String address, GeoLocation location, MultipartFile image) throws Exception {
        //
        String username = SpaceContext.get().getUsername();

        Lendee lendee = lendeeLogic.findByPhoneNumber(username);
        lendee.setName(name);
        Profile profile = lendee.getProfile();
        profile.setGender(gender);
        profile.setEmail(email);
        profile.setAddress(address);
        profile.setLocation(location);
        if (image != null && !image.isEmpty()) {
            String path = minioService.uploadFile(image).getUrl();
            profile.setAvatarUrl(path);
        }
        lendee.setProfile(profile);
        lendeeLogic.modifyLendee(lendee);

        return lendee.getId();
    }

    public String updateNotificationPreferences(boolean emailRentalReminders,
                                                boolean emailNewMessages,
                                                boolean pushRentalReminders,
                                                boolean pushNewMessages,
                                                boolean pushPromotionsAndDeals,
                                                boolean smsRentalReminders,
                                                boolean smsNewMessages,
                                                boolean marketingPromotionsAndDeals,
                                                boolean marketingEmails) {
        //
        String username = SpaceContext.get().getUsername();

        Lendee lendee = lendeeLogic.findByPhoneNumber(username);

        LendeeNotificationPreferences.EmailNotifications lendeeEmailNotifications = new LendeeNotificationPreferences.EmailNotifications(
            emailRentalReminders,
            emailNewMessages
        );

        LendeeNotificationPreferences.PushNotifications lendeePushNotifications = new LendeeNotificationPreferences.PushNotifications(
            pushRentalReminders,
            pushNewMessages,
            pushPromotionsAndDeals
        );

        LendeeNotificationPreferences.SmsNotifications lendeeSmsNotifications = new LendeeNotificationPreferences.SmsNotifications(
            smsRentalReminders,
            smsNewMessages
        );

        LendeeNotificationPreferences.MarketingNotifications lendeeMarketingNotifications = new LendeeNotificationPreferences.MarketingNotifications(
            marketingPromotionsAndDeals,
            marketingEmails
        );

        LendeeNotificationPreferences notificationPreferences = new LendeeNotificationPreferences(
            lendeeEmailNotifications,
            lendeePushNotifications,
            lendeeSmsNotifications,
            lendeeMarketingNotifications
        );

        lendee.setNotificationPreferences(notificationPreferences);
        lendeeLogic.modifyLendee(lendee);

        return lendee.getId();
    }

    public String updatePrivacySettings(LendeePrivacySettings.ProfileVisibility profileVisibility,
                                        LendeePrivacySettings.DataAndLocation dataAndLocation) {
        String username = SpaceContext.get().getUsername();
        Lendee lendee = lendeeLogic.findByPhoneNumber(username);

        LendeePrivacySettings privacySettings = new LendeePrivacySettings(profileVisibility, dataAndLocation);
        lendee.setPrivacySettings(privacySettings);
        lendeeLogic.modifyLendee(lendee);

        return lendee.getId();
    }

    public String updateSecuritySettings(boolean twoFactorAuthentication,
                                         boolean loginAlertsForNewDevices,
                                         int sessionTimeoutMinutes) {
        String username = SpaceContext.get().getUsername();
        Lendee lendee = lendeeLogic.findByPhoneNumber(username);

        LendeeSecuritySettings securitySettings = new LendeeSecuritySettings(
            twoFactorAuthentication,
            loginAlertsForNewDevices,
            sessionTimeoutMinutes
        );
        lendee.setSecuritySettings(securitySettings);
        lendeeLogic.modifyLendee(lendee);

        return lendee.getId();
    }

    public String updateAppearanceSettings(boolean darkMode,
                                         boolean compactView,
                                         String defaultLanguage) {
        String username = SpaceContext.get().getUsername();
        Lendee lendee = lendeeLogic.findByPhoneNumber(username);

        LendeeAppearanceSettings.Theme theme = new LendeeAppearanceSettings.Theme(
            darkMode,
            compactView
        );

        LendeeAppearanceSettings appearanceSettings = new LendeeAppearanceSettings(
            theme,
            defaultLanguage
        );

        lendee.setAppearanceSettings(appearanceSettings);
        lendeeLogic.modifyLendee(lendee);

        return lendee.getId();
    }

    public String changePassword(String currentPassword, String newPassword) {
        String username = SpaceContext.get().getUsername();
        Account account = accountLogic.findByPhoneNumberAndRole(username, Role.ROLE_RENTER.name());

        Assert.isTrue(passwordEncoder.matches(currentPassword, account.getPassword()),
            "Current password is incorrect");

        account.setPassword(passwordEncoder.encode(newPassword));
        accountLogic.modifyAccount(account);

        return account.getId();
    }
}
