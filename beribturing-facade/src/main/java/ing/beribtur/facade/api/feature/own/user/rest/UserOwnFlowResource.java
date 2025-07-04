package ing.beribtur.facade.api.feature.own.user.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.aggregate.user.entity.vo.Gender;
import ing.beribtur.aggregate.user.entity.vo.GeoLocation;
import ing.beribtur.facade.api.feature.own.user.command.ModifyProfileOwnCommand;
import ing.beribtur.facade.api.feature.own.user.command.UpdateNotificationPreferencesOwnCommand;
import ing.beribtur.feature.own.user.flow.UserOwnFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/feature/owner/user")
@RequiredArgsConstructor
public class UserOwnFlowResource implements UserOwnFlowFacade {
    //
    private final UserOwnFlow userOwnFlow;

    @Override
    @PostMapping(value = "/modify-profile/command", consumes = "multipart/form-data")
    public CommandResponse<String> modifyProfile(@RequestPart("command") ModifyProfileOwnCommand command, @RequestPart(value = "image", required = false) MultipartFile image) throws Exception {
        //
        command.validate();
        String name = command.getName();
        Gender gender = command.getGender();
        String email = command.getEmail();
        String address = command.getAddress();
        GeoLocation location = command.getLocation();
        String entityId = userOwnFlow.modifyProfile(name, gender, email, address, location, image);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/update-notification-preferences/command")
    public CommandResponse<String> updateNotificationPreferences(@RequestBody UpdateNotificationPreferencesOwnCommand command) throws Exception {
        //
        command.validate();
        boolean emailNewBookingsAndReservations = command.getEmailNotifications().isNewBookingsAndReservations();
        boolean emailMessagesFromCustomers = command.getEmailNotifications().isMessagesFromCustomers();
        boolean emailPaymentConfirmations = command.getEmailNotifications().isPaymentConfirmations();

        boolean smsNewBookingsAndReservations = command.getSmsNotifications().isNewBookingsAndReservations();
        boolean smsMessagesFromCustomers = command.getSmsNotifications().isMessagesFromCustomers();
        boolean smsPaymentConfirmations = command.getSmsNotifications().isPaymentConfirmations();

        String entityId = userOwnFlow.updateNotificationPreferences(emailNewBookingsAndReservations, emailMessagesFromCustomers, emailPaymentConfirmations, smsNewBookingsAndReservations, smsMessagesFromCustomers, smsPaymentConfirmations);
        return new CommandResponse<>(entityId);
    }
}
