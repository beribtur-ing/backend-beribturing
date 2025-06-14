package ing.beribtur.facade.api.feature.own.rental.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.own.rental.command.*;

public interface RentalOwnFlowFacade {
    //
    CommandResponse<String> registerRentalRecord(RegisterRentalRecordOwnCommand command);
    CommandResponse<String> modifyRentalRecord(ModifyRentalRecordOwnCommand command);
    CommandResponse<String> registerItemConditionCheck(RegisterItemConditionCheckOwnCommand command);
    CommandResponse<String> registerItemConditionPhoto(RegisterItemConditionPhotoOwnCommand command);
    CommandResponse<String> approveReservation(ApproveReservationOwnCommand command);
    CommandResponse<String> rejectReservation(RejectReservationOwnCommand command);
}
