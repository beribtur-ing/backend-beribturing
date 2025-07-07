package ing.beribtur.facade.api.feature.adm.lendee.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.facade.api.feature.adm.lendee.query.FindLendeeAdmQuery;
import ing.beribtur.facade.api.feature.adm.lendee.query.FindLendeesAdmQuery;

import java.util.List;

public interface LendeeAdmSeekFacade {
    QueryResponse<List<Lendee>> findLendeesAdmin(FindLendeesAdmQuery query);
    QueryResponse<Lendee> findLendeeAdmin(FindLendeeAdmQuery query);
}