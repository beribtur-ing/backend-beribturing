package ing.beribtur.feature.rnt.user.seek;

import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.logic.AccountLogic;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.logic.LendeeLogic;
import ing.beribtur.feature.shared.sdo.UserMeRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .phoneNumber(account.getPhoneNumber())
                .email(account.getEmail())
                .role(account.getRole())
                .name(lendee.getName())
                .build();
    }
} 