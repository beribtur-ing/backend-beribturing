package ing.beribtur.accent.domain;

import ing.beribtur.accent.util.JsonSerializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Getter
@Setter
public class NameValueList implements JsonSerializable {
    //
    private List<NameValue> nameValues;

    private NameValueList(NameValueList nameValues) {
        this();
        this.nameValues = new java.util.ArrayList<>();
        this.nameValues.addAll(nameValues.list());
    }

    public static NameValueList from(NameValueList nameValues) {
        return new NameValueList(nameValues);
    }

    public static NameValueList newInstance() {
        //
        NameValueList nameValueList = new NameValueList();
        nameValueList.nameValues = new java.util.ArrayList<>();
        return nameValueList;
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

        for (NameValue nameValue : this.nameValues) {
            if (nameValue.getName().equals(name)) {
                targetNameValue = nameValue;
                break;
            }
        }

        if (targetNameValue != null) {
            this.nameValues.remove(targetNameValue);
        }
    }

    public void add(NameValue nameValue) {
        //
        if (nameValue == null || nameValue.getName() == null) {
            throw new IllegalArgumentException("NameValue or its name cannot be null.");
        }
        if (this.containsName(nameValue.getName())) {
            this.remove(nameValue.getName());
        }
        this.nameValues.add(nameValue);
    }

    public boolean isEmpty() {
        //
        return this.nameValues.isEmpty();
    }
}
