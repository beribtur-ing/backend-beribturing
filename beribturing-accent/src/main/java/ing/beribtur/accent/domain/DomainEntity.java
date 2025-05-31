package ing.beribtur.accent.domain;

import ing.beribtur.accent.context.SpaceContext;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

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
        return Objects.hash(new Object[]{this.id});
    }
}
