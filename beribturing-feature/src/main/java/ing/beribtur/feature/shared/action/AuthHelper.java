package ing.beribtur.feature.shared.action;

import ing.beribtur.accent.context.SpaceContext;
import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.entity.vo.Role;
import ing.beribtur.aggregate.account.logic.AccountLogic;
import ing.beribtur.config.security.UserDetailsLogic;
import ing.beribtur.config.security.jwt.JwtUtils;
import ing.beribtur.feature.shared.sdo.AccountSignInTokenRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthHelper {
    //
    private final AccountLogic accountLogic;
    private final JwtUtils jwtUtils;

    public String currentUserId(Role role) {
        //
        String phoneNumber = SpaceContext.get().getUsername();
        Account currentAccount = accountLogic.findByPhoneNumberAndRole(phoneNumber, role.name());
        return currentAccount.getId();
    }

    public AccountSignInTokenRdo refreshAccessToken(String refreshToken) {
        if (!jwtUtils.validateJwtToken(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        String username = jwtUtils.getUsernameFromToken(refreshToken);
        String role = (String) jwtUtils.getClaimFromToken(refreshToken, claims -> claims.get("role"));
        
        if (role == null || role.trim().isEmpty()) {
            throw new RuntimeException("Role not found in refresh token");
        }
        
        Account account = accountLogic.findByPhoneNumberAndRole(username, role);
        
        if (!account.isEnabled() || !account.isAccountNonExpired() || !account.isAccountNonLocked()) {
            throw new RuntimeException("Account is not active");
        }
        
        UserDetailsLogic userDetails = UserDetailsLogic.build(account);

        String newAccessToken = jwtUtils.generateAccessToken(userDetails, role);
        String newRefreshToken = jwtUtils.generateRefreshToken(userDetails, role);

        return AccountSignInTokenRdo.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }
}
