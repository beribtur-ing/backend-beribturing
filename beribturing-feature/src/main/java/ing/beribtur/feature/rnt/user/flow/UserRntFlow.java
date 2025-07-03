package ing.beribtur.feature.rnt.user.flow;

import ing.beribtur.accent.context.SpaceContext;
import ing.beribtur.aggregate.user.entity.vo.LendeeNotificationPreferences;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.entity.vo.Gender;
import ing.beribtur.aggregate.user.entity.vo.GeoLocation;
import ing.beribtur.aggregate.user.entity.vo.Profile;
import ing.beribtur.aggregate.user.entity.vo.LendeePrivacySettings;
import ing.beribtur.aggregate.user.logic.LendeeLogic;
import ing.beribtur.proxy.minio.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRntFlow {
    //
    private final LendeeLogic lendeeLogic;
    private final MinioService minioService;

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
            String path = minioService.uploadFile(image);
            profile.setAvatarUrl(path);
        }
        lendeeLogic.update(lendee);

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
        lendeeLogic.update(lendee);

        return lendee.getId();
    }

    public String updatePrivacySettings(LendeePrivacySettings.ProfileVisibility profileVisibility,
                                      LendeePrivacySettings.DataAndLocation dataAndLocation) {
        String username = SpaceContext.get().getUsername();
        Lendee lendee = lendeeLogic.findByPhoneNumber(username);
        
        LendeePrivacySettings privacySettings = new LendeePrivacySettings(profileVisibility, dataAndLocation);
        lendee.setPrivacySettings(privacySettings);
        lendeeLogic.update(lendee);
        
        return lendee.getId();
    }
} 