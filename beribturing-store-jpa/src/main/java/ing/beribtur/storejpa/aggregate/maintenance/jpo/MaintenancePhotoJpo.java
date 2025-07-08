package ing.beribtur.storejpa.aggregate.maintenance.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.maintenance.entity.MaintenancePhoto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MAINTENANCE_PHOTO")
public class MaintenancePhotoJpo extends DomainEntityJpo {
    //
    private String maintenanceRequestId;
    @Column(columnDefinition = "varchar(500)")
    private String url;
    @Column(columnDefinition = "text")
    private String description;
    private int photoOrder;

    public MaintenancePhotoJpo(MaintenancePhoto maintenancePhoto) {
        //
        super(maintenancePhoto);
        BeanUtils.copyProperties(maintenancePhoto, this);
    }

    public MaintenancePhotoJpo toDomain() {
        //
        MaintenancePhotoJpo maintenanceRequest = new MaintenancePhotoJpo();
        BeanUtils.copyProperties(this, maintenanceRequest);
        return maintenanceRequest;
    }

    public static List<MaintenancePhotoJpo> toDomains(List<MaintenancePhotoJpo> jpos) {
        //
        return jpos.stream().map(MaintenancePhotoJpo::toDomain).toList();
    }
}
