package ing.beribtur.facade.api.feature.adm.auth.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.auth.rdo.AccountSignInTokenRdo;
import ing.beribtur.facade.api.feature.adm.auth.query.AccountSignInAdmQuery;
import ing.beribtur.feature.adm.auth.seek.AuthAdmSeek;
import lombok.RequiredArgsConstructor;
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
    public QueryResponse<AccountSignInTokenRdo> accountSignIn(@RequestBody AccountSignInAdmQuery query) {
        //
        query.validate();
        AccountSignInTokenRdo rdo = this.authAdmSeek.accountSignInAdm(query.getPhoneNumber(), query.getPassword());
        return new QueryResponse<>(rdo);
    }
}
