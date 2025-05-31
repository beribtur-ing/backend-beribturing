package ing.beribtur.aggregate.user.entity.vo;

import ing.beribtur.accent.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Profile implements ValueObject {
    //
    private Gender gender;
    private String email;
    private String address;
    private String avatarUrl;
    private GeoLocation location;
}
