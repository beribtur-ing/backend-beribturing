package ing.beribtur.facade.api.feature.adm.lender.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.user.entity.Lender;
import ing.beribtur.facade.api.feature.adm.lender.query.FindLenderAdmQuery;
import ing.beribtur.facade.api.feature.adm.lender.query.FindLendersAdmQuery;

import java.util.List;

public interface LenderAdmSeekFacade {
    QueryResponse<List<Lender>> findLendersAdmin(FindLendersAdmQuery query);
    QueryResponse<Lender> findLenderAdmin(FindLenderAdmQuery query);
}