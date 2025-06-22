package ing.beribtur.facade.api.feature.rnt.user.command;

import ing.beribtur.accent.message.CommandRequest;
import ing.beribtur.aggregate.user.entity.vo.Gender;
import ing.beribtur.aggregate.user.entity.vo.GeoLocation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class ModifyProfileRntCommand extends CommandRequest<String> {
    //
    private String name;
    private Gender gender;
    private String email;
    private String address;
    private GeoLocation location;

    public void validate() {
        //
        Assert.hasText(name, "Name is required");
    }
} 