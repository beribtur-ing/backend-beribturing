package ing.beribtur.facade.api.feature.own.auth.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.auth.rdo.AccountSignInTokenRdo;
import ing.beribtur.facade.api.feature.own.auth.query.AccountSignInOwnQuery;

public interface AuthOwnSeekFacade {
    //
    QueryResponse<AccountSignInTokenRdo> accountSignIn(AccountSignInOwnQuery query);
}
