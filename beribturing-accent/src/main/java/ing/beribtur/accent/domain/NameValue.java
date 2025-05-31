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
public class NameValue implements Serializable {
    //
    private String name;
    private String value;

    public static NameValue of(String name, String value) {
        return new NameValue(name, value);
    }
}
