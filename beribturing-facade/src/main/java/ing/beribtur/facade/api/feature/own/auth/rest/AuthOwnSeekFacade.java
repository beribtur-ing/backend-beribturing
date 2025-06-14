package ing.beribtur.facade.api.feature.own.auth.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.facade.api.feature.own.auth.query.AccountSignInOwnQuery;
import ing.beribtur.feature.shared.sdo.AccountSignInTokenRdo;

public interface AuthOwnSeekFacade {
    //
    QueryResponse<AccountSignInTokenRdo> accountSignIn(AccountSignInOwnQuery query);
}
