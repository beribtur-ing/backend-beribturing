package ing.beribtur.facade.api.feature.adm.auth.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.auth.rdo.AccountSignInTokenRdo;
import ing.beribtur.facade.api.feature.adm.auth.query.AccountSignInAdmQuery;

public interface AuthAdmSeekFacade {
    //
    QueryResponse<AccountSignInTokenRdo> accountSignIn(AccountSignInAdmQuery query);
}
