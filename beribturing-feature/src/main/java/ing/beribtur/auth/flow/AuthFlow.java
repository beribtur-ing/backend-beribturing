package ing.beribtur.auth.flow;

import ing.beribtur.aggregate.account.entity.Account;
import ing.beribtur.aggregate.account.logic.AccountLogic;
import ing.beribtur.auth.rdo.AccountSignInTokenRdo;
import ing.beribtur.config.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthFlow {
    //
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtil;
    private final AccountLogic accountLogic;

    public AccountSignInTokenRdo accountSignIn(String phoneNumber, String password) {
        //
        Account account = accountLogic.findByPhoneNumber(phoneNumber);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(phoneNumber, password)
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String accessToken = jwtUtil.generateAccessToken(userDetails);
        String refreshToken = jwtUtil.generateRefreshToken(userDetails);
        return AccountSignInTokenRdo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
