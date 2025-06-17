package ing.beribtur.feature.adm.user.seek;

import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.logic.AccountLogic;
import ing.beribtur.feature.shared.sdo.UserMeRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static ing.beribtur.aggregate.account.entity.vo.Role.ROLE_ADMIN;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAdmSeek {
    private final AccountLogic accountLogic;

    public UserMeRdo userMe() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String phoneNumber = securityContext.getAuthentication().getName();
        Account account = accountLogic.findByPhoneNumberAndRole(phoneNumber, ROLE_ADMIN.name());
        return UserMeRdo.builder()
                .phoneNumber(account.getPhoneNumber())
                .email(account.getEmail())
                .role(account.getRole())
                .build();
    }
}
