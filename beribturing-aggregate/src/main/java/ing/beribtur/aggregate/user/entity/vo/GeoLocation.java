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
public class GeoLocation implements ValueObject {
    //
    private double latitude;
    private double longitude;
}
