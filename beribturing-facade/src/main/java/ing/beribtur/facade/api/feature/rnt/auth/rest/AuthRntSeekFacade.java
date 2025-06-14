package ing.beribtur.facade.api.feature.rnt.auth.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.facade.api.feature.rnt.auth.query.AccountSignInRntQuery;
import ing.beribtur.feature.shared.sdo.AccountSignInTokenRdo;

public interface AuthRntSeekFacade {
    //
    QueryResponse<AccountSignInTokenRdo> accountSignIn(AccountSignInRntQuery query);
}
