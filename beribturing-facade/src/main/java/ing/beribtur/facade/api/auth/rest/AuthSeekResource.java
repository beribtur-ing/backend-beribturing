package ing.beribtur.facade.api.auth.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.auth.flow.AuthFlow;
import ing.beribtur.auth.rdo.AccountSignInTokenRdo;
import ing.beribtur.facade.api.auth.query.AccountSignInQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthSeekResource implements AuthSeekFacade{
    //
    private final AuthFlow authFlow;

    @Override
    @PostMapping("/sign-in/query")
    public QueryResponse<AccountSignInTokenRdo> accountSignIn(@RequestBody AccountSignInQuery query) {
        //
        query.validate();
        AccountSignInTokenRdo rdo = this.authFlow.accountSignIn(query.getPhoneNumber(), query.getPassword());
        return new QueryResponse<>(rdo);
    }
}
