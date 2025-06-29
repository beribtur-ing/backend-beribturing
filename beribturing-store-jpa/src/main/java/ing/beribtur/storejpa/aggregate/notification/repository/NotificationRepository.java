package ing.beribtur.storejpa.aggregate.notification.repository;

import ing.beribtur.storejpa.aggregate.notification.jpo.NotificationJpo;
import ing.beribtur.aggregate.notification.entity.vo.ChannelType;
import ing.beribtur.aggregate.notification.entity.vo.NotificationType;
import ing.beribtur.aggregate.notification.entity.vo.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationJpo, String> {
    List<NotificationJpo> findByReceiverId(String receiverId);
    List<NotificationJpo> findByStatus(Status status);
    List<NotificationJpo> findByChannelType(ChannelType channelType);
    List<NotificationJpo> findByReceiverIdAndStatus(String receiverId, Status status);
    List<NotificationJpo> findByType(NotificationType type);
    List<NotificationJpo> findBySenderId(String senderId);

    @Query("SELECT COALESCE(MAX(CAST(SUBSTRING(n.id, LENGTH(:senderId) + 2) AS long)), 0) + 1 FROM NotificationJpo n WHERE n.senderId = :senderId AND n.id LIKE CONCAT(:senderId, '-%')")
    long getNextSequenceForSender(@Param("senderId") String senderId);

    Page<NotificationJpo> findByReceiverIdAndStatusOrderBySentAtDesc(String receiverId, Status status, Pageable pageable);
    Page<NotificationJpo> findByReceiverIdAndStatusAndTypeOrderBySentAtDesc(String receiverId, Status status, NotificationType type, Pageable pageable);
    Page<NotificationJpo> findByReceiverIdAndStatusAndChannelTypeOrderBySentAtDesc(String receiverId, Status status, ChannelType channelType, Pageable pageable);
    Page<NotificationJpo> findByReceiverIdAndStatusAndTypeAndChannelTypeOrderBySentAtDesc(String receiverId, Status status, NotificationType type, ChannelType channelType, Pageable pageable);

}
