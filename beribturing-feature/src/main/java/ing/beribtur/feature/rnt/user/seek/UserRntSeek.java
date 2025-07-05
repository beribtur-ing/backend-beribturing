package ing.beribtur.feature.rnt.user.seek;

import ing.beribtur.accent.context.SpaceContext;
import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.logic.AccountLogic;
import ing.beribtur.aggregate.chat.logic.ChatMessageLogic;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.entity.vo.*;
import ing.beribtur.aggregate.user.logic.LendeeLogic;
import ing.beribtur.aggregate.item.logic.ProductLogic;
import ing.beribtur.aggregate.rental.logic.ReservationLogic;
import ing.beribtur.aggregate.payment.logic.TransactionLogic;
import ing.beribtur.aggregate.notification.logic.NotificationLogic;
import ing.beribtur.feature.shared.sdo.*;
import ing.beribtur.feature.rnt.user.rdo.LendeeCurrentInfoRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static ing.beribtur.aggregate.account.entity.vo.Role.ROLE_RENTER;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRntSeek {
    private final AccountLogic accountLogic;
    private final LendeeLogic lendeeLogic;
    private final ProductLogic productLogic;
    private final ReservationLogic reservationLogic;
    private final TransactionLogic transactionLogic;
    private final NotificationLogic notificationLogic;
    private final ChatMessageLogic chatMessageLogic;

    public UserMeRdo userMe() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String phoneNumber = securityContext.getAuthentication().getName();
        Account account = accountLogic.findByPhoneNumberAndRole(phoneNumber, ROLE_RENTER.name());
        Lendee lendee = lendeeLogic.findLendee(account.getId());

        return UserMeRdo.builder()
                .avatarUrl(Optional.ofNullable(lendee.getProfile()).map(Profile::getAvatarUrl).orElse(null))
                .address(Optional.ofNullable(lendee.getProfile()).map(Profile::getAddress).orElse(null))
                .location(Optional.ofNullable(lendee.getProfile()).map(Profile::getLocation).orElse(null))
                .gender(Optional.ofNullable(lendee.getProfile()).map(Profile::getGender).orElse(null))
                .phoneNumber(account.getPhoneNumber())
                .email(Optional.ofNullable(lendee.getProfile()).map(Profile::getEmail).orElse(null))
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

    public LendeeCurrentInfoRdo genLendeeCurrentStatistic() {
        //
        String username = SpaceContext.get().getUsername();
        Lendee lendee = lendeeLogic.findByPhoneNumber(username);
        int countOwnedProducts = productLogic.countOwnedProducts(lendee.getId());

        int activeBookingsCount = reservationLogic.countActiveBookingsOfOwner(lendee.getId());

        LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfMonth = startOfMonth.plusMonths(1);

        double monthExpenses = transactionLogic.calculateMonthlyRevenueOfOwner(username, startOfMonth, endOfMonth);

        int unReadMessageCount = chatMessageLogic.countUnreadMessageOfUser(username);

        return new LendeeCurrentInfoRdo(
                countOwnedProducts,
                activeBookingsCount,
                monthExpenses,
                unReadMessageCount
        );
    }
}
