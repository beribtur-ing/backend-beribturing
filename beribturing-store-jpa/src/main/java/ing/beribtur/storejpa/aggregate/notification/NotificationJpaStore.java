package ing.beribtur.storejpa.aggregate.notification;

import ing.beribtur.aggregate.notification.entity.Notification;
import ing.beribtur.aggregate.notification.entity.vo.*;
import ing.beribtur.aggregate.notification.store.NotificationStore;
import ing.beribtur.storejpa.aggregate.notification.jpo.NotificationJpo;
import ing.beribtur.storejpa.aggregate.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class NotificationJpaStore implements NotificationStore {
    //
    private final NotificationRepository notificationRepository;
    private final ContactJpaStore contactJpaStore;

    @Override
    public void create(Notification notification) {
        NotificationJpo notificationJpo = new NotificationJpo(notification);

        notificationRepository.save(notificationJpo);
        notification.setId(notificationJpo.getId());
    }

    @Override
    public Notification retrieve(String id) {
        NotificationJpo notificationJpo = notificationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Notification not found: " + id));
        return notificationJpo.toDomain();
    }

    @Override
    public List<Notification> retrieveAll(List<String> ids) {
        List<NotificationJpo> notificationJpos = notificationRepository.findAllById(ids);
        return notificationJpos.stream()
                .map(NotificationJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Notification notification) {
        NotificationJpo notificationJpo = notificationRepository.findById(notification.getId())
                .orElseThrow(() -> new IllegalArgumentException("Notification not found: " + notification.getId()));

        // Update the JPO with the domain entity's values
        NotificationJpo updatedJpo = new NotificationJpo(notification);
        BeanUtils.copyProperties(notificationJpo, updatedJpo);

        notificationRepository.save(updatedJpo);
    }

    @Override
    public void delete(String id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public List<Notification> retrieveByReceiverId(String receiverId) {
        return NotificationJpo.toDomains(notificationRepository.findByReceiverId(receiverId));
    }

    @Override
    public List<Notification> retrieveByStatus(Status status) {
        return NotificationJpo.toDomains(notificationRepository.findByStatus(status));
    }

    @Override
    public List<Notification> retrieveByChannelType(ChannelType channelType) {
        return NotificationJpo.toDomains(notificationRepository.findByChannelType(channelType));
    }

    @Override
    public List<Notification> retrieveByReceiverIdAndStatus(String receiverId, Status status) {
        return NotificationJpo.toDomains(notificationRepository.findByReceiverIdAndStatus(receiverId, status));
    }

    // Additional query methods
    public List<Notification> findByType(NotificationType type) {
        return NotificationJpo.toDomains(notificationRepository.findByType(type));
    }

    public List<Notification> findBySenderId(String senderId) {
        return NotificationJpo.toDomains(notificationRepository.findBySenderId(senderId));
    }

}
