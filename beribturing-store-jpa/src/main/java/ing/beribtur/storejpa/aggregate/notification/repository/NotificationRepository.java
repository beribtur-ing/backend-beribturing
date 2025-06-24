package ing.beribtur.storejpa.aggregate.notification.repository;

import ing.beribtur.storejpa.aggregate.notification.jpo.NotificationJpo;
import ing.beribtur.aggregate.notification.entity.vo.ChannelType;
import ing.beribtur.aggregate.notification.entity.vo.NotificationType;
import ing.beribtur.aggregate.notification.entity.vo.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationJpo, String> {
    List<NotificationJpo> findByReceiverId(String receiverId);
    List<NotificationJpo> findByStatus(Status status);
    List<NotificationJpo> findByChannelType(ChannelType channelType);
    List<NotificationJpo> findByReceiverIdAndStatus(String receiverId, Status status);
    List<NotificationJpo> findByType(NotificationType type);
    List<NotificationJpo> findBySenderId(String senderId);
}