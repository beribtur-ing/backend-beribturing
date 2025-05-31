package ing.beribtur.accent.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IdName implements Serializable {
    //
    private String id;
    private String name;

    public static IdName of(String id, String name) {
        //
        return new IdName(id, name);
    }
}
