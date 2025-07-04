package ing.beribtur.feature.own.user.flow;

import ing.beribtur.accent.context.SpaceContext;
import ing.beribtur.aggregate.user.entity.vo.LenderNotificationPreferences;
import ing.beribtur.aggregate.user.entity.Lender;
import ing.beribtur.aggregate.user.entity.vo.Gender;
import ing.beribtur.aggregate.user.entity.vo.GeoLocation;
import ing.beribtur.aggregate.user.entity.vo.Profile;
import ing.beribtur.aggregate.user.logic.LenderLogic;
import ing.beribtur.proxy.minio.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class UserOwnFlow {
    //
    private final LenderLogic lenderLogic;
    private final MinioService minioService;

    public String modifyProfile(String name, Gender gender, String email, String address, GeoLocation location, MultipartFile image) throws Exception {
        //
        String username = SpaceContext.get().getUsername();

        Lender lender = lenderLogic.findByPhoneNumber(username);
        lender.setName(name);
        Profile profile = lender.getProfile();
        profile.setGender(gender);
        profile.setEmail(email);
        profile.setAddress(address);
        profile.setLocation(location);
        if (image != null && !image.isEmpty()) {
            String path = minioService.uploadFile(image);
            profile.setAvatarUrl(path);
        }
        lenderLogic.modifyLender(lender);

        return lender.getId();
    }

    public String updateNotificationPreferences(boolean emailNewBookingsAndReservations,
                                                boolean emailMessagesFromCustomers,
                                                boolean emailPaymentConfirmations,
                                                boolean smsNewBookingsAndReservations,
                                                boolean smsMessagesFromCustomers,
                                                boolean smsPaymentConfirmations) {
        //
        String username = SpaceContext.get().getUsername();

        Lender lender = lenderLogic.findByPhoneNumber(username);

        LenderNotificationPreferences.EmailNotifications lenderEmailNotifications = new LenderNotificationPreferences.EmailNotifications(
                emailNewBookingsAndReservations,
                emailMessagesFromCustomers,
                emailPaymentConfirmations
        );

        LenderNotificationPreferences.SmsNotifications lenderSmsNotifications = new LenderNotificationPreferences.SmsNotifications(
                smsNewBookingsAndReservations,
                smsMessagesFromCustomers,
                smsPaymentConfirmations
        );

        LenderNotificationPreferences notificationPreferences = new LenderNotificationPreferences(lenderEmailNotifications, lenderSmsNotifications);
        lender.setNotificationPreferences(notificationPreferences);
        lenderLogic.modifyLender(lender);

        return lender.getId();
    }

    public LenderNotificationPreferences getNotificationPreferences() {
        String username = SpaceContext.get().getUsername();
        Lender lender = lenderLogic.findByPhoneNumber(username);

        LenderNotificationPreferences preferences = lender.getNotificationPreferences();
        if (preferences == null) {
            return LenderNotificationPreferences.createDefault();
        }
        return preferences;
    }
}
