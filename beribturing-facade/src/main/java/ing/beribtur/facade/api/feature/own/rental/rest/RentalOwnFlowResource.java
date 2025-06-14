package ing.beribtur.facade.api.feature.own.rental.rest;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.aggregate.rental.entity.sdo.ItemConditionCheckCdo;
import ing.beribtur.aggregate.rental.entity.sdo.ItemConditionPhotoCdo;
import ing.beribtur.aggregate.rental.entity.sdo.RentalRecordCdo;
import ing.beribtur.facade.api.feature.own.rental.command.ApproveReservationOwnCommand;
import ing.beribtur.facade.api.feature.own.rental.command.ModifyRentalRecordOwnCommand;
import ing.beribtur.facade.api.feature.own.rental.command.RegisterItemConditionCheckOwnCommand;
import ing.beribtur.facade.api.feature.own.rental.command.RegisterItemConditionPhotoOwnCommand;
import ing.beribtur.facade.api.feature.own.rental.command.RegisterRentalRecordOwnCommand;
import ing.beribtur.facade.api.feature.own.rental.command.RejectReservationOwnCommand;
import ing.beribtur.feature.own.rental.flow.RentalOwnFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/owner/rental")
public class RentalOwnFlowResource implements RentalOwnFlowFacade {
    //
    private final RentalOwnFlow rentalOwnFlow;

    @Override
    @PostMapping("/register-rental-record/command")
    public CommandResponse<String> registerRentalRecord(@RequestBody RegisterRentalRecordOwnCommand command) {
        //
        command.validate();
        RentalRecordCdo rentalRecordCdo = command.getRentalRecordCdo();
        String entityId = rentalOwnFlow.registerRentalRecord(rentalRecordCdo);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/modify-rental-record/command")
    public CommandResponse<String> modifyRentalRecord(@RequestBody ModifyRentalRecordOwnCommand command) {
        //
        command.validate();
        String rentalRecordId = command.getRentalRecordId();
        NameValueList nameValueList = command.getNameValueList();
        String entityId = rentalOwnFlow.modifyRentalRecord(rentalRecordId, nameValueList);
        return new CommandResponse<>(entityId);
    }


    @Override
    @PostMapping("/register-item-condition-check/command")
    public CommandResponse<String> registerItemConditionCheck(@RequestBody RegisterItemConditionCheckOwnCommand command) {
        //
        command.validate();
        ItemConditionCheckCdo itemConditionCheckCdo = command.getItemConditionCheckCdo();
        String entityId = rentalOwnFlow.registerItemConditionCheck(itemConditionCheckCdo);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/register-item-condition-photo/command")
    public CommandResponse<String> registerItemConditionPhoto(@RequestBody RegisterItemConditionPhotoOwnCommand command) {
        //
        command.validate();
        ItemConditionPhotoCdo itemConditionPhotoCdo = command.getItemConditionPhotoCdo();
        String entityId = rentalOwnFlow.registerItemConditionPhoto(itemConditionPhotoCdo);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/approve-reservation/command")
    public CommandResponse<String> approveReservation(@RequestBody ApproveReservationOwnCommand command) {
        //
        command.validate();
        String reservationId = command.getReservationId();
        rentalOwnFlow.approveReservation(reservationId);
        return new CommandResponse<>(reservationId);
    }

    @Override
    @PostMapping("/reject-reservation/command")
    public CommandResponse<String> rejectReservation(@RequestBody RejectReservationOwnCommand command) {
        //
        command.validate();
        String reservationId = command.getReservationId();
        rentalOwnFlow.rejectReservation(reservationId);
        return new CommandResponse<>(reservationId);
    }
}
