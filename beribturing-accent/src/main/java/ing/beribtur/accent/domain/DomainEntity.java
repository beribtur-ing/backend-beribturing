package ing.beribtur.accent.domain;

import ing.beribtur.accent.context.SpaceContext;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

@Getter
@Setter
public abstract class DomainEntity implements Serializable {
    //
    protected String id;
    protected long entityVersion;
    private String registeredBy;
    private LocalDateTime registeredOn;
    private String modifiedBy;
    private LocalDateTime modifiedOn;

    public DomainEntity() {
        //
        this.entityVersion = 0L;
        this.registeredOn = LocalDateTime.now();
        this.registeredBy = SpaceContext.get().getUsername();
        this.modifiedBy = registeredBy;
        this.modifiedOn = registeredOn;
    }

    protected DomainEntity(String id) {
        //
        this();
        this.id = id;
    }

    protected abstract void modifyAttributes(NameValueList var1);

    public void modify(NameValueList nameValues) {
        NameValueList modificationNameValues = NameValueList.from(nameValues);
        if (this.hasEntityVersion(modificationNameValues)) {
            this.entityVersion = Long.parseLong(modificationNameValues.getValueOf("entityVersion"));
            modificationNameValues.remove("entityVersion");
        }

        this.modifyAttributes(modificationNameValues);
        this.modifiedBy = SpaceContext.get().getUsername();
        this.modifiedOn = LocalDateTime.now();
    }

    private boolean hasEntityVersion(NameValueList nameValues) {
        return nameValues.containsName("entityVersion") && Pattern.compile("[+-]?\\d+").matcher(nameValues.getValueOf("entityVersion")).matches();
    }

    @Override
    public boolean equals(Object target) {
        //
        if (this == target) {
            return true;
        } else if (target != null && this.getClass() == target.getClass()) {
            DomainEntity entity = (DomainEntity) target;
            return Objects.equals(this.id, entity.id);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
