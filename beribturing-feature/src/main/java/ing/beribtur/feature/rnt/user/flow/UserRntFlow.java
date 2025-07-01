package ing.beribtur.feature.rnt.user.flow;

import ing.beribtur.accent.context.SpaceContext;
import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.entity.vo.RenterNotificationPreferences;
import ing.beribtur.aggregate.account.entity.vo.Role;
import ing.beribtur.aggregate.account.logic.AccountLogic;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.entity.vo.Gender;
import ing.beribtur.aggregate.user.entity.vo.GeoLocation;
import ing.beribtur.aggregate.user.entity.vo.Profile;
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
    private final AccountLogic accountLogic;

    public String modifyProfile(String name, Gender gender, String email, String address, GeoLocation location, MultipartFile image) throws Exception {
        //
        String username = SpaceContext.get().getUsername();

        Account account = accountLogic.findByPhoneNumberAndRole(username, Role.ROLE_RENTER.name());
        account.setEmail(email);
        accountLogic.update(account);

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

        Account account = accountLogic.findByPhoneNumberAndRole(username, Role.ROLE_RENTER.name());
        
        RenterNotificationPreferences.EmailNotifications renterEmailNotifications = new RenterNotificationPreferences.EmailNotifications(
            emailRentalReminders,
            emailNewMessages
        );
        
        RenterNotificationPreferences.PushNotifications renterPushNotifications = new RenterNotificationPreferences.PushNotifications(
            pushRentalReminders,
            pushNewMessages,
            pushPromotionsAndDeals
        );
        
        RenterNotificationPreferences.SmsNotifications renterSmsNotifications = new RenterNotificationPreferences.SmsNotifications(
            smsRentalReminders,
            smsNewMessages
        );
        
        RenterNotificationPreferences.MarketingNotifications renterMarketingNotifications = new RenterNotificationPreferences.MarketingNotifications(
            marketingPromotionsAndDeals,
            marketingEmails
        );
        
        RenterNotificationPreferences notificationPreferences = new RenterNotificationPreferences(
            renterEmailNotifications,
            renterPushNotifications,
            renterSmsNotifications,
            renterMarketingNotifications
        );
        
        account.setNotificationPreferences(notificationPreferences);
        accountLogic.update(account);

        return account.getId();
    }
} 