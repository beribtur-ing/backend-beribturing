package ing.beribtur.facade.api.feature.rnt.rental.rest;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.aggregate.rental.entity.sdo.ItemConditionCheckCdo;
import ing.beribtur.aggregate.rental.entity.sdo.ItemConditionPhotoCdo;
import ing.beribtur.aggregate.rental.entity.sdo.ReservationCdo;
import ing.beribtur.facade.api.feature.rnt.rental.command.ModifyRentalRecordRntCommand;
import ing.beribtur.facade.api.feature.rnt.rental.command.ModifyReservationRntCommand;
import ing.beribtur.facade.api.feature.rnt.rental.command.RegisterItemConditionCheckRntCommand;
import ing.beribtur.facade.api.feature.rnt.rental.command.RegisterItemConditionPhotoRntCommand;
import ing.beribtur.facade.api.feature.rnt.rental.command.RegisterReservationRntCommand;
import ing.beribtur.feature.rnt.rental.flow.RentalRntFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/renter/rental")
public class RentalRntFlowResource implements RentalRntFlowFacade {
    //
    private final RentalRntFlow rentalRntFlow;

    @Override
    @PostMapping("/register-reservation/command")
    public CommandResponse<String> registerReservation(@RequestBody RegisterReservationRntCommand command) {
        //
        command.validate();
        ReservationCdo reservationCdo = command.getReservationCdo();
        String entityId = rentalRntFlow.registerReservation(reservationCdo);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/modify-reservation/command")
    public CommandResponse<String> modifyReservation(@RequestBody ModifyReservationRntCommand command) {
        //
        command.validate();
        String reservationId = command.getReservationId();
        NameValueList nameValueList = command.getNameValueList();
        String entityId = rentalRntFlow.modifyReservation(reservationId, nameValueList);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/modify-rental-record/command")
    public CommandResponse<String> modifyRentalRecord(@RequestBody ModifyRentalRecordRntCommand command) {
        //
        command.validate();
        String rentalRecordId = command.getRentalRecordId();
        NameValueList nameValueList = command.getNameValueList();
        String entityId = rentalRntFlow.modifyRentalRecord(rentalRecordId, nameValueList);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/register-item-condition-check/command")
    public CommandResponse<String> registerItemConditionCheck(@RequestBody RegisterItemConditionCheckRntCommand command) {
        //
        command.validate();
        ItemConditionCheckCdo itemConditionCheckCdo = command.getItemConditionCheckCdo();
        String entityId = rentalRntFlow.registerItemConditionCheck(itemConditionCheckCdo);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/register-item-condition-photo/command")
    public CommandResponse<String> registerItemConditionPhoto(@RequestBody RegisterItemConditionPhotoRntCommand command) {
        //
        command.validate();
        ItemConditionPhotoCdo itemConditionPhotoCdo = command.getItemConditionPhotoCdo();
        String entityId = rentalRntFlow.registerItemConditionPhoto(itemConditionPhotoCdo);
        return new CommandResponse<>(entityId);
    }
}