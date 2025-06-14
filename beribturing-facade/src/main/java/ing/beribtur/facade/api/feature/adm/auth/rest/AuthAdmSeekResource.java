package ing.beribtur.facade.api.feature.adm.auth.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.facade.api.feature.adm.auth.query.AccountSignInAdmQuery;
import ing.beribtur.feature.adm.auth.seek.AuthAdmSeek;
import ing.beribtur.feature.shared.sdo.AccountSignInTokenRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feature/admin/auth")
@RequiredArgsConstructor
public class AuthAdmSeekResource implements AuthAdmSeekFacade{
    //
    private final AuthAdmSeek authAdmSeek;

    @Override
    @PostMapping("/sign-in/query")
    public QueryResponse<AccountSignInTokenRdo> accountSignIn(@RequestBody AccountSignInAdmQuery query) {
        //
        query.validate();
        AccountSignInTokenRdo rdo = this.authAdmSeek.accountSignInAdm(query.getPhoneNumber(), query.getPassword());
        return new QueryResponse<>(rdo);
    }
}
