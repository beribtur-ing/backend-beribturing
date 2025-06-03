package ing.beribtur.accent.domain;

import ing.beribtur.accent.util.JsonSerializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NameValue implements JsonSerializable {
    //
    private String name;
    private String value;

    public static NameValue of(String name, String value) {
        return new NameValue(name, value);
    }
}
