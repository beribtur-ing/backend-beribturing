package ing.beribtur.storejpa.aggregate.notification;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.notification.entity.Notification;
import ing.beribtur.aggregate.notification.entity.vo.ChannelType;
import ing.beribtur.aggregate.notification.entity.vo.Status;
import ing.beribtur.aggregate.notification.store.NotificationStore;
import ing.beribtur.feature.notification.sdo.NotificationSearchQdo;
import ing.beribtur.storejpa.aggregate.notification.jpo.NotificationJpo;
import ing.beribtur.storejpa.aggregate.notification.repository.NotificationRepository;
import ing.beribtur.storejpa.aggregate.user.jpo.LenderJpo;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
public class NotificationJpaStore implements NotificationStore {
    //
    private final NotificationRepository notificationRepository;

    @Override
    public void create(Notification notification) {
        //
        NotificationJpo notificationJpo = new NotificationJpo(notification);
        notificationRepository.save(notificationJpo);
    }

    @Override
    public void createAll(List<Notification> notifications) {
        //
        notificationRepository.saveAll(notifications.stream().map(NotificationJpo::new).collect(Collectors.toList()));
    }

    @Override
    public Notification retrieve(String id) {
        //
        return notificationRepository.findById(id)
            .map(NotificationJpo::toDomain)
            .orElse(null);
    }

    @Override
    public List<Notification> retrieveAll(List<String> notificationIds) {
        //
        Iterable<NotificationJpo> allById = notificationRepository.findAllById(notificationIds);
        return NotificationJpo.toDomains(StreamSupport.stream(allById.spliterator(), false).collect(Collectors.toList()));
    }

    @Override
    public List<Notification> retrieveList(Offset offset) {
        //
        Pageable pageable = PageRequest.of(offset.page(), offset.limit());
        return notificationRepository.findAll(pageable).map(NotificationJpo::toDomain).toList();
    }

    @Override
    public void update(Notification notification) {
        //
        notificationRepository.save(new NotificationJpo(notification));
    }

    @Override
    public void delete(Notification notification) {
        //
        notificationRepository.delete(new NotificationJpo(notification));
    }

    @Override
    public void delete(String id) {
        //
        notificationRepository.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        //
        return notificationRepository.existsById(id);
    }

    @Override
    public List<Notification> retrieveByReceiverId(String receiverId) {
        //
        return notificationRepository.findByReceiverId(receiverId).stream()
            .map(NotificationJpo::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<Notification> retrieveBySenderId(String senderId) {
        //
        return notificationRepository.findBySenderId(senderId).stream()
            .map(NotificationJpo::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<Notification> retrieveByStatus(Status status) {
        //
        return notificationRepository.findByStatus(status).stream()
            .map(NotificationJpo::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<Notification> retrieveByChannelType(ChannelType channelType) {
        //
        return notificationRepository.findByChannelType(channelType).stream()
            .map(NotificationJpo::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<Notification> retrieveByReceiverIdAndStatus(String receiverId, Status status) {
        //
        return notificationRepository.findByReceiverIdAndStatus(receiverId, status).stream()
            .map(NotificationJpo::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public long getNextSequence(String senderId) {
        //
        return notificationRepository.getNextSequenceForSender(senderId);
    }

    @Override
    public Page<Notification> retrieveUnreadNotifications(String receiverId, Offset offset) {
        //
        Pageable pageable = PageRequest.of(offset.page(), offset.limit());
        Page<NotificationJpo> jpos = this.notificationRepository.findByReceiverIdAndStatusOrderBySentAtDesc(receiverId, Status.SENT, pageable);
        return new PageImpl<>(
                NotificationJpo.toDomains(jpos.getContent()),
                pageable,
                jpos.getTotalElements()
        );
    }
}
