package ing.beribtur.accent.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
public abstract class DomainEntityJpo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Version
    protected long entityVersion;
    private String registeredBy;
    private long registeredOn;
    private String modifiedBy;
    private long modifiedOn;

    public DomainEntityJpo() {
        //
        this.entityVersion = 0L;
        this.registeredOn  = System.currentTimeMillis();
    }

    protected DomainEntityJpo(DomainEntity domainEntity) {
        //
        this.id = domainEntity.getId();
        this.entityVersion = domainEntity.getEntityVersion();
        this.registeredBy = domainEntity.getRegisteredBy();
        this.registeredOn = domainEntity.getRegisteredOn();
        this.modifiedBy = domainEntity.getRegisteredBy();
        this.modifiedOn = domainEntity.getModifiedOn();
    }

    protected DomainEntityJpo(Long id) {
        //
        this();
        this.id = id;
    }

    @Override
    public boolean equals(Object target) {
        if (this == target) {
            return true;
        } else if (target != null && this.getClass() == target.getClass()) {
            DomainEntityJpo entity = (DomainEntityJpo)target;
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
