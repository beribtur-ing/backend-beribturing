package ing.beribtur.feature.rnt.auth.seek;

import ing.beribtur.aggregate.account.entity.vo.Role;
import ing.beribtur.aggregate.account.logic.AccountLogic;
import ing.beribtur.config.security.jwt.JwtUtils;
import ing.beribtur.feature.shared.sdo.AccountSignInTokenRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthRntSeek {
    //
    private final AccountLogic accountLogic;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AccountSignInTokenRdo accountSignInRnt(String phoneNumber, String password) {
        String roleName = Role.ROLE_RENTER.name();
        accountLogic.findByPhoneNumberAndRole(phoneNumber, roleName);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(phoneNumber + "-" + roleName, password, List.of(new SimpleGrantedAuthority(roleName))));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String accessToken = jwtUtils.generateAccessToken(userDetails, roleName);
        String refreshToken = jwtUtils.generateRefreshToken(userDetails, roleName);
        return AccountSignInTokenRdo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
