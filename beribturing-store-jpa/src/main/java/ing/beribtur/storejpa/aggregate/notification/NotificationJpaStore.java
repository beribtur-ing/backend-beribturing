package ing.beribtur.storejpa.aggregate.notification;

import ing.beribtur.aggregate.notification.entity.Notification;
import ing.beribtur.aggregate.notification.entity.vo.NotificationType;
import ing.beribtur.aggregate.notification.store.NotificationStore;
import ing.beribtur.storejpa.aggregate.notification.jpo.NotificationJpo;
import ing.beribtur.storejpa.aggregate.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class NotificationJpaStore implements NotificationStore {
    //
    private final NotificationRepository notificationRepository;

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
        updatedJpo.setEntityVersion(notificationJpo.getEntityVersion());
        updatedJpo.setRegisteredBy(notificationJpo.getRegisteredBy());
        updatedJpo.setRegisteredOn(notificationJpo.getRegisteredOn());
        
        notificationRepository.save(updatedJpo);
    }

    @Override
    public void delete(String id) {
        notificationRepository.deleteById(id);
    }
    
    // Additional methods for specific queries
    public List<Notification> findByRecipientId(String recipientId) {
        return NotificationJpo.toDomains(notificationRepository.findByRecipientId(recipientId));
    }
    
    public List<Notification> findByIsRead(boolean isRead) {
        return NotificationJpo.toDomains(notificationRepository.findByRead(isRead));
    }
    
    public List<Notification> findByType(NotificationType type) {
        return NotificationJpo.toDomains(notificationRepository.findByType(type.name()));
    }
    
    public List<Notification> findByRecipientIdAndIsRead(String recipientId, boolean isRead) {
        return NotificationJpo.toDomains(notificationRepository.findByRecipientIdAndRead(recipientId, isRead));
    }
}