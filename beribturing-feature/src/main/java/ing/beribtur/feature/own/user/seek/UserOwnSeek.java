package ing.beribtur.feature.own.user.seek;

import ing.beribtur.accent.context.SpaceContext;
import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.logic.AccountLogic;
import ing.beribtur.aggregate.user.entity.Lender;
import ing.beribtur.aggregate.user.entity.vo.LenderNotificationPreferences;
import ing.beribtur.aggregate.user.entity.vo.Profile;
import ing.beribtur.aggregate.user.logic.LenderLogic;
import ing.beribtur.feature.shared.sdo.UserMeRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static ing.beribtur.aggregate.account.entity.vo.Role.ROLE_OWNER;

@Service
@Transactional
@RequiredArgsConstructor
public class UserOwnSeek {
    private final AccountLogic accountLogic;
    private final LenderLogic lenderLogic;

    public UserMeRdo userMe() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String phoneNumber = securityContext.getAuthentication().getName();
        Account account = accountLogic.findByPhoneNumberAndRole(phoneNumber, ROLE_OWNER.name());
        Lender lender = lenderLogic.retrieve(account.getId());

        return UserMeRdo.builder()
                .avatarUrl(Optional.ofNullable(lender.getProfile()).map(Profile::getAvatarUrl).orElse(null))
                .address(Optional.ofNullable(lender.getProfile()).map(Profile::getAddress).orElse(null))
                .location(Optional.ofNullable(lender.getProfile()).map(Profile::getLocation).orElse(null))
                .gender(Optional.ofNullable(lender.getProfile()).map(Profile::getGender).orElse(null))
                .phoneNumber(account.getPhoneNumber())
                .email(account.getEmail())
                .role(account.getRole())
                .name(lender.getName())
                .build();
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