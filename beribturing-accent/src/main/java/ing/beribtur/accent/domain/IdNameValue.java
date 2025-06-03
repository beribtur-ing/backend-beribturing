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
public class IdNameValue implements JsonSerializable {
    //
    private String id;
    private String name;
    private String value;

    public static IdNameValue of(String id, String name, String value) {
        //
        return new IdNameValue(id, name, value);
    }
}
