package ing.beribtur.accent.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Getter
@Setter
public class NameValueList implements Serializable {
    //
    private List<NameValue> nameValues;

    private NameValueList(NameValueList nameValues) {
        this();
        this.nameValues.addAll(nameValues.list());
    }

    public static NameValueList from(NameValueList nameValues) {
        return new NameValueList(nameValues);
    }

    public boolean containsName(String name) {
        return this.nameValues.stream().anyMatch((nv) -> nv.getName().equals(name));
    }

    public String getValueOf(String name) {
        return Optional.ofNullable(this.getNameValue(name)).map(NameValue::getValue).orElse(null);
    }

    public NameValue getNameValue(String name) {
        return this.nameValues.stream().filter((nameValue) -> name.equals(nameValue.getName())).findFirst().orElse(null);
    }

    public List<NameValue> list() {
        return this.nameValues;
    }

    public void remove(String name) {
        NameValue targetNameValue = null;

        for(NameValue nameValue : this.nameValues) {
            if (nameValue.getName().equals(name)) {
                targetNameValue = nameValue;
                break;
            }
        }

        if (targetNameValue != null) {
            this.nameValues.remove(targetNameValue);
        }
    }
}
