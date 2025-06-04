package ing.beribtur.facade.api.feature.own.rental.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.own.rental.command.ModifyRentalRecordOwnCommand;
import ing.beribtur.facade.api.feature.own.rental.command.RegisterItemConditionCheckOwnCommand;
import ing.beribtur.facade.api.feature.own.rental.command.RegisterItemConditionPhotoOwnCommand;
import ing.beribtur.facade.api.feature.own.rental.command.RegisterRentalRecordOwnCommand;

public interface RentalOwnFlowFacade {
    //
    CommandResponse<String> registerRentalRecord(RegisterRentalRecordOwnCommand command);
    CommandResponse<String> modifyRentalRecord(ModifyRentalRecordOwnCommand command);
    CommandResponse<String> registerItemConditionCheck(RegisterItemConditionCheckOwnCommand command);
    CommandResponse<String> registerItemConditionPhoto(RegisterItemConditionPhotoOwnCommand command);
}