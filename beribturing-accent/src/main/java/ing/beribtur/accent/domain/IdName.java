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
public class IdName implements JsonSerializable {
    //
    private String id;
    private String name;

    public static IdName of(String id, String name) {
        //
        return new IdName(id, name);
    }
}
