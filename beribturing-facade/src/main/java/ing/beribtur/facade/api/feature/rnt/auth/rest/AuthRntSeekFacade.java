package ing.beribtur.facade.api.feature.rnt.auth.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.auth.rdo.AccountSignInTokenRdo;
import ing.beribtur.facade.api.feature.rnt.auth.query.AccountSignInRntQuery;

public interface AuthRntSeekFacade {
    //
    QueryResponse<AccountSignInTokenRdo> accountSignIn(AccountSignInRntQuery query);
}
