package ing.beribtur.feature.adm.auth.seek;

import ing.beribtur.aggregate.account.entity.vo.Role;
import ing.beribtur.aggregate.account.logic.AccountLogic;
import ing.beribtur.config.security.jwt.JwtUtils;
import ing.beribtur.feature.shared.sdo.AccountSignInTokenRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthAdmSeek {
    //
    private final AccountLogic accountLogic;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AccountSignInTokenRdo accountSignInAdm(String phoneNumber, String password) {
        String roleName = Role.ROLE_ADMIN.name();
        accountLogic.findByPhoneNumberAndRole(phoneNumber, roleName);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(phoneNumber, password));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String accessToken = jwtUtils.generateAccessToken(userDetails, roleName);
        String refreshToken = jwtUtils.generateRefreshToken(userDetails, roleName);
        return AccountSignInTokenRdo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
