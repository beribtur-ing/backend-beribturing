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
public class IdNameValue implements Serializable {
    //
    private Long id;
    private String name;
    private String value;

    public static IdNameValue of(Long id, String name, String value) {
        //
        return new IdNameValue(id, name, value);
    }
}
