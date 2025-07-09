package ing.beribtur.feature.adm.rental.seek;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.feature.shared.customstore.RentalRecordCustomStore;
import ing.beribtur.feature.shared.sdo.RentalRecordRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RentalAdmSeek {
    //
    private final RentalRecordCustomStore rentalRecordCustomStore;

    public Page<RentalRecordRdo> findRentalRecords(String lendeeId, Offset offset) {
        return rentalRecordCustomStore.findRentalRecordsByLendee(lendeeId, offset);
    }
    //

}
