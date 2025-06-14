package ing.beribtur.facade.api.feature.own.auth.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.facade.api.feature.own.auth.query.AccountSignInOwnQuery;
import ing.beribtur.feature.own.auth.seek.AuthOwnSeek;
import ing.beribtur.feature.shared.sdo.AccountSignInTokenRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feature/owner/auth")
@RequiredArgsConstructor
public class AuthOwnSeekResource implements AuthOwnSeekFacade {
    //
    private final AuthOwnSeek authOwnSeek;

    @Override
    @RequestMapping("/sign-in/query")
    public QueryResponse<AccountSignInTokenRdo> accountSignIn(@RequestBody AccountSignInOwnQuery query) {
        //
        query.validate();
        AccountSignInTokenRdo rdo = this.authOwnSeek.accountSignInOwn(query.getPhoneNumber(), query.getPassword());
        return new QueryResponse<>(rdo);
    }
}
