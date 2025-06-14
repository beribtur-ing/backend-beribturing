package ing.beribtur.facade.api.feature.own.rental.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.own.rental.command.ApproveReservationOwnCommand;
import ing.beribtur.facade.api.feature.own.rental.command.ModifyRentalRecordOwnCommand;
import ing.beribtur.facade.api.feature.own.rental.command.RegisterItemConditionCheckOwnCommand;
import ing.beribtur.facade.api.feature.own.rental.command.RegisterItemConditionPhotoOwnCommand;
import ing.beribtur.facade.api.feature.own.rental.command.RegisterRentalRecordOwnCommand;
import ing.beribtur.facade.api.feature.own.rental.command.RejectReservationOwnCommand;

public interface RentalOwnFlowFacade {
    //
    CommandResponse<String> registerRentalRecord(RegisterRentalRecordOwnCommand command);
    CommandResponse<String> modifyRentalRecord(ModifyRentalRecordOwnCommand command);
    CommandResponse<String> registerItemConditionCheck(RegisterItemConditionCheckOwnCommand command);
    CommandResponse<String> registerItemConditionPhoto(RegisterItemConditionPhotoOwnCommand command);
    CommandResponse<String> approveReservation(ApproveReservationOwnCommand command);
    CommandResponse<String> rejectReservation(RejectReservationOwnCommand command);
}
