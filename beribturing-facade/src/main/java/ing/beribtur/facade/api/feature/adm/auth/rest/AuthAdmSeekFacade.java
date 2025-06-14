package ing.beribtur.facade.api.feature.adm.auth.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.facade.api.feature.adm.auth.query.AccountSignInAdmQuery;
import ing.beribtur.feature.shared.sdo.AccountSignInTokenRdo;

public interface AuthAdmSeekFacade {
    //
    QueryResponse<AccountSignInTokenRdo> accountSignIn(AccountSignInAdmQuery query);
}
