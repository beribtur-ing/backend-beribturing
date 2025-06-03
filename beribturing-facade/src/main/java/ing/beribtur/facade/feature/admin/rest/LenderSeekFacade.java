package ing.beribtur.facade.feature.admin.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.aggregate.user.entity.Lender;
import ing.beribtur.facade.feature.admin.command.FindDisabledLendersAdminQuery;

import java.util.List;

public interface LenderSeekFacade {
    QueryResponse<List<Lender>> findDisabledLendersAdmin(FindDisabledLendersAdminQuery command);
}
