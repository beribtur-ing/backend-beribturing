package ing.beribtur.facade.api.feature.rnt.rental.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.rnt.rental.command.*;

public interface RentalRntFlowFacade {
    //
    CommandResponse<String> registerReservation(RegisterReservationRntCommand command);
    CommandResponse<String> modifyReservation(ModifyReservationRntCommand command);
    CommandResponse<String> modifyRentalRecord(ModifyRentalRecordRntCommand command);
    CommandResponse<String> registerItemConditionCheck(RegisterItemConditionCheckRntCommand command);
    CommandResponse<String> registerItemConditionPhoto(RegisterItemConditionPhotoRntCommand command);
}
