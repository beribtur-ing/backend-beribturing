package ing.beribtur.facade.api.feature.rnt.auth.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.facade.api.feature.rnt.auth.query.AccountSignInRntQuery;
import ing.beribtur.feature.rnt.auth.seek.AuthRntSeek;
import ing.beribtur.feature.shared.rdo.AccountSignInTokenRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feature/renter/auth")
@RequiredArgsConstructor
public class AuthRntSeekResource implements AuthRntSeekFacade {
    //
    private final AuthRntSeek authRntSeek;

    @Override
    @PostMapping("/sign-in/query")
    public QueryResponse<AccountSignInTokenRdo> accountSignIn(@RequestBody AccountSignInRntQuery query) {
        //
        query.validate();
        AccountSignInTokenRdo rdo = this.authRntSeek.accountSignInRnt(query.getPhoneNumber(), query.getPassword());
        return new QueryResponse<>(rdo);
    }
}
