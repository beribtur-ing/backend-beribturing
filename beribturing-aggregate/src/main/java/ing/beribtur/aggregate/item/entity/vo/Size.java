package ing.beribtur.aggregate.item.entity.vo;

import ing.beribtur.accent.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Size implements ValueObject {
    //
    private String label;        // Optional: S, M, L, XL, etc.
    private Double width;        // in cm or inches
    private Double height;
    private Double depth;
    private Double weight;       // in kg
    private String measureUnit;  // metric or imperial
}
