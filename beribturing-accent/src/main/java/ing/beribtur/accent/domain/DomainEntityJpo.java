package ing.beribtur.accent.domain;

import ing.beribtur.accent.util.JsonSerializable;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class DomainEntityJpo implements JsonSerializable {
    @Id
    protected String id;
    @Version
    protected long entityVersion;
    private String registeredBy;
    private String modifiedBy;

    @CreationTimestamp
    @Column(updatable = false, nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE default now()")
    private LocalDateTime registeredOn;

    @CreationTimestamp
    @Column(updatable = false, nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE default now()")
    private LocalDateTime modifiedOn;

    public DomainEntityJpo() {
        //
        this.id = UUID.randomUUID().toString();
        this.entityVersion = 0L;
        this.registeredOn = LocalDateTime.now();
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

    protected DomainEntityJpo(String id) {
        //
        this();
        this.id = id;
    }

    @Override
    public boolean equals(Object target) {
        if (this == target) {
            return true;
        } else if (target != null && this.getClass() == target.getClass()) {
            DomainEntityJpo entity = (DomainEntityJpo) target;
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
