package ing.beribtur.storejpa.aggregate.maintenance.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.maintenance.entity.MaintenanceRequest;
import ing.beribtur.aggregate.maintenance.entity.vo.MaintenanceStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MAINTENANCE_REQUEST")
public class MaintenanceRequestJpo extends DomainEntityJpo {
    //
    private String productVariantId;
    private String requesterId;
    private String ownerId;
    private String rentalRecordId;
    @Column(columnDefinition = "text")
    private String description;
    @Enumerated(EnumType.STRING)
    private MaintenanceStatus status;
    private LocalDateTime requestedAt;
    private LocalDateTime resolvedAt;
    @Column(columnDefinition = "text")
    private String resolution;

    public MaintenanceRequestJpo(MaintenanceRequest maintenanceRequest) {
        //
        super(maintenanceRequest);
        BeanUtils.copyProperties(maintenanceRequest, this);
    }

    public MaintenanceRequest toDomain() {
        //
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        BeanUtils.copyProperties(this, maintenanceRequest);
        return maintenanceRequest;
    }

    public static List<MaintenanceRequest> toDomains(List<MaintenanceRequestJpo> jpos) {
        //
        return jpos.stream().map(MaintenanceRequestJpo::toDomain).toList();
    }
}