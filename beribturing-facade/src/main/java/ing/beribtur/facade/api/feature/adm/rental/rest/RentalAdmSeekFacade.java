package ing.beribtur.facade.api.feature.adm.rental.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.facade.api.feature.adm.rental.query.FindRentalRecordsAdmQuery;
import ing.beribtur.feature.shared.sdo.RentalRecordRdo;

import java.util.List;

public interface RentalAdmSeekFacade {
    //
    QueryResponse<List<RentalRecordRdo>> findRentalRecords(FindRentalRecordsAdmQuery query);
}
