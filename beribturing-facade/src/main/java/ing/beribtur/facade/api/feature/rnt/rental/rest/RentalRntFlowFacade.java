package ing.beribtur.facade.api.feature.rnt.rental.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.rnt.rental.command.ModifyRentalRecordRntCommand;
import ing.beribtur.facade.api.feature.rnt.rental.command.ModifyReservationRntCommand;
import ing.beribtur.facade.api.feature.rnt.rental.command.RegisterItemConditionCheckRntCommand;
import ing.beribtur.facade.api.feature.rnt.rental.command.RegisterItemConditionPhotoRntCommand;
import ing.beribtur.facade.api.feature.rnt.rental.command.RegisterReservationRntCommand;

public interface RentalRntFlowFacade {
    //
    CommandResponse<String> registerReservation(RegisterReservationRntCommand command);
    CommandResponse<String> modifyReservation(ModifyReservationRntCommand command);
    CommandResponse<String> modifyRentalRecord(ModifyRentalRecordRntCommand command);
    CommandResponse<String> registerItemConditionCheck(RegisterItemConditionCheckRntCommand command);
    CommandResponse<String> registerItemConditionPhoto(RegisterItemConditionPhotoRntCommand command);
}