package ing.beribtur.facade.api.feature.rnt.user.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.aggregate.user.entity.vo.Gender;
import ing.beribtur.aggregate.user.entity.vo.GeoLocation;
import ing.beribtur.facade.api.feature.rnt.user.command.ModifyProfileRntCommand;
import ing.beribtur.facade.api.feature.rnt.user.command.UpdateNotificationPreferencesRntCommand;
import ing.beribtur.facade.api.feature.rnt.user.command.UpdateSecuritySettingsRntCommand;
import ing.beribtur.facade.api.feature.rnt.user.command.UpdateAppearanceSettingsRntCommand;
import ing.beribtur.facade.api.feature.own.user.command.UpdatePrivacySettingsRntCommand;
import ing.beribtur.feature.rnt.user.flow.UserRntFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/feature/renter/user")
@RequiredArgsConstructor
public class UserRntFlowResource implements UserRntFlowFacade {
    //
    private final UserRntFlow userRntFlow;

    @Override
    @PostMapping(value = "/modify-profile/command", consumes = "multipart/form-data")
    public CommandResponse<String> modifyProfile(@RequestPart("command") ModifyProfileRntCommand command, @RequestPart(value = "image", required = false) MultipartFile image) throws Exception {
        //
        command.validate();
        String name = command.getName();
        Gender gender = command.getGender();
        String email = command.getEmail();
        String address = command.getAddress();
        GeoLocation location = command.getLocation();
        String entityId = userRntFlow.modifyProfile(name, gender, email, address, location, image);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/update-notification-preferences/command")
    public CommandResponse<String> updateNotificationPreferences(@RequestBody UpdateNotificationPreferencesRntCommand command) throws Exception {
        //
        command.validate();

        boolean emailRentalReminders = command.getEmailNotifications().isRentalReminders();
        boolean emailNewMessages = command.getEmailNotifications().isNewMessages();

        boolean pushRentalReminders = command.getPushNotifications().isRentalReminders();
        boolean pushNewMessages = command.getPushNotifications().isNewMessages();
        boolean pushPromotionsAndDeals = command.getPushNotifications().isPromotionsAndDeals();

        boolean smsRentalReminders = command.getSmsNotifications().isRentalReminders();
        boolean smsNewMessages = command.getSmsNotifications().isNewMessages();

        boolean marketingPromotionsAndDeals = command.getMarketingNotifications().isPromotionsAndDeals();
        boolean marketingEmails = command.getMarketingNotifications().isMarketingEmails();

        String entityId = userRntFlow.updateNotificationPreferences(emailRentalReminders, emailNewMessages, pushRentalReminders, pushNewMessages, pushPromotionsAndDeals, smsRentalReminders, smsNewMessages, marketingPromotionsAndDeals, marketingEmails);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/update-privacy-settings/command")
    public CommandResponse<String> updatePrivacySettings(@RequestBody UpdatePrivacySettingsRntCommand command) throws Exception {
        command.validate();
        
        String entityId = userRntFlow.updatePrivacySettings(
            command.getProfileVisibility(),
            command.getDataAndLocation()
        );
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/update-security-settings/command")
    public CommandResponse<String> updateSecuritySettings(@RequestBody UpdateSecuritySettingsRntCommand command) throws Exception {
        command.validate();
        
        String entityId = userRntFlow.updateSecuritySettings(
            command.isTwoFactorAuthentication(),
            command.isLoginAlertsForNewDevices(),
            command.getSessionTimeoutMinutes()
        );
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/update-appearance-settings/command")
    public CommandResponse<String> updateAppearanceSettings(@RequestBody UpdateAppearanceSettingsRntCommand command) throws Exception {
        command.validate();
        
        String entityId = userRntFlow.updateAppearanceSettings(
            command.isDarkMode(),
            command.isCompactView(),
            command.getDefaultLanguage()
        );
        return new CommandResponse<>(entityId);
    }
}