package ing.beribtur.storejpa.aggregate.notification.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.notification.entity.Notification;
import ing.beribtur.aggregate.notification.entity.vo.NotificationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "NOTIFICATION")
public class NotificationJpo extends DomainEntityJpo {

    @Column(nullable = false)
    private String recipientId;
    
    @Column(nullable = false)
    private String message;
    
    @Column(nullable = false)
    private String type;
    
    private boolean isRead;
    
    @Column(nullable = false)
    private LocalDateTime timestamp;

    public NotificationJpo(Notification notification) {
        //
        super(notification);
        BeanUtils.copyProperties(notification, this);
        
        // Handle enum conversion
        if (notification.getType() != null) {
            this.type = notification.getType().name();
        }
    }

    public Notification toDomain() {
        //
        Notification notification = new Notification();
        BeanUtils.copyProperties(this, notification);
        
        // Convert string back to enum
        if (this.type != null) {
            notification.setType(NotificationType.valueOf(this.type));
        }
        
        return notification;
    }

    public static List<Notification> toDomains(List<NotificationJpo> jpos) {
        //
        return jpos.stream().map(NotificationJpo::toDomain).toList();
    }
}