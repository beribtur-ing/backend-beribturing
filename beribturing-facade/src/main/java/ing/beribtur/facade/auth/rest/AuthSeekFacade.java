package ing.beribtur.facade.auth.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.auth.rdo.AccountSignInTokenRdo;
import ing.beribtur.facade.auth.query.AccountSignInQuery;

public interface AuthSeekFacade {
    //
    QueryResponse<AccountSignInTokenRdo> accountSignIn(AccountSignInQuery query);
}
