package ing.beribtur.feature.shared.sdo;

import ing.beribtur.aggregate.user.entity.vo.NotificationPreferences;
import ing.beribtur.aggregate.account.entity.vo.Role;
import ing.beribtur.aggregate.user.entity.vo.Gender;
import ing.beribtur.aggregate.user.entity.vo.GeoLocation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserMeRdo {
    private String avatarUrl;
    private String address;
    private GeoLocation location;
    private Gender gender;
    private String phoneNumber;
    private String email;
    private Role role;
    private String name;
    private NotificationPreferences notificationPreferences;
}
