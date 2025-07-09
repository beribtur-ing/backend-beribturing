package ing.beribtur.facade.api.feature.adm.rental.rest;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.facade.api.feature.adm.rental.query.FindRentalRecordsAdmQuery;
import ing.beribtur.feature.adm.rental.seek.RentalAdmSeek;
import ing.beribtur.feature.shared.sdo.RentalRecordRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/admin/rental")
public class RentalAdmSeekResource implements RentalAdmSeekFacade {
    //
    private final RentalAdmSeek rentalAdmSeek;

    @Override
    @PostMapping("/find-rental-record-rdos/query")
    public QueryResponse<List<RentalRecordRdo>> findRentalRecords(@RequestBody FindRentalRecordsAdmQuery query) {
        query.validate();
        String lendeeId = query.getLendeeId();
        Offset offset = query.getOffset();
        Page<RentalRecordRdo> response = rentalAdmSeek.findRentalRecords(lendeeId, offset);
        query.setResponse(response);
        return query.getResponse();
    }
    //

}
