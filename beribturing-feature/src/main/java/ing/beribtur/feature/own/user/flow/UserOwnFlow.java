package ing.beribtur.feature.own.user.flow;

import ing.beribtur.accent.context.SpaceContext;
import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.entity.vo.OwnerNotificationPreferences;
import ing.beribtur.aggregate.account.entity.vo.Role;
import ing.beribtur.aggregate.account.logic.AccountLogic;
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
    private final AccountLogic accountLogic;

    public String modifyProfile(String name, Gender gender, String email, String address, GeoLocation location, MultipartFile image) throws Exception {
        //
        String username = SpaceContext.get().getUsername();

        Account account = accountLogic.findByPhoneNumberAndRole(username, Role.ROLE_OWNER.name());
        account.setEmail(email);
        accountLogic.update(account);

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
        lenderLogic.update(lender);

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

        Account account = accountLogic.findByPhoneNumberAndRole(username, Role.ROLE_OWNER.name());

        OwnerNotificationPreferences.EmailNotifications ownerEmailNotifications = new OwnerNotificationPreferences.EmailNotifications(
                emailNewBookingsAndReservations,
                emailMessagesFromCustomers,
                emailPaymentConfirmations
        );

        OwnerNotificationPreferences.SmsNotifications ownerSmsNotifications = new OwnerNotificationPreferences.SmsNotifications(
                smsNewBookingsAndReservations,
                smsMessagesFromCustomers,
                smsPaymentConfirmations
        );

        OwnerNotificationPreferences notificationPreferences = new OwnerNotificationPreferences(ownerEmailNotifications, ownerSmsNotifications);
        account.setNotificationPreferences(notificationPreferences);
        accountLogic.update(account);

        return account.getId();
    }
}
