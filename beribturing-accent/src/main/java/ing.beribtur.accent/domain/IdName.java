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
    private Long id;
    private String name;

    public static IdName of(Long id, String name) {
        //
        return new IdName(id, name);
    }
}
