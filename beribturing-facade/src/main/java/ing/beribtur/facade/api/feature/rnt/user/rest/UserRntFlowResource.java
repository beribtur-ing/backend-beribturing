package ing.beribtur.facade.api.feature.rnt.user.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.aggregate.user.entity.vo.Gender;
import ing.beribtur.aggregate.user.entity.vo.GeoLocation;
import ing.beribtur.facade.api.feature.rnt.user.command.ModifyProfileRntCommand;
import ing.beribtur.feature.rnt.user.flow.UserRntFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/feature/renter/user")
@RequiredArgsConstructor
public class UserRntFlowResource implements UserRntFlowFacade {
    //
    private final UserRntFlow userRntFlow;

    @Override
    @PostMapping(value = "/modify-profile/command", consumes = "multipart/form-data")
    public CommandResponse<String> modifyProfile(@RequestPart("command") ModifyProfileRntCommand command, @RequestPart(value = "image", required = false) MultipartFile image) throws Exception {
        //
        command.validate();
        String name = command.getName();
        Gender gender = command.getGender();
        String email = command.getEmail();
        String address = command.getAddress();
        GeoLocation location = command.getLocation();
        String entityId = userRntFlow.modifyProfile(name, gender, email, address, location, image);
        return new CommandResponse<>(entityId);
    }
}