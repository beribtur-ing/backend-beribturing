package ing.beribtur.storejpa.aggregate.notification.repository;

import ing.beribtur.storejpa.aggregate.notification.jpo.NotificationJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationJpo, String> {
    List<NotificationJpo> findByRecipientId(String recipientId);
    List<NotificationJpo> findByRead(boolean isRead);
    List<NotificationJpo> findByType(String type);
    List<NotificationJpo> findByRecipientIdAndRead(String recipientId, boolean isRead);
}