package ing.beribtur.storejpa.aggregate.chat.repository;

import ing.beribtur.storejpa.aggregate.chat.jpo.ChatMessageJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessageJpo, String> {
    List<ChatMessageJpo> findBySenderId(String senderId);
    List<ChatMessageJpo> findByReceiverId(String receiverId);
    List<ChatMessageJpo> findBySenderIdAndReceiverId(String senderId, String receiverId);
    List<ChatMessageJpo> findByRead(boolean isRead);
    List<ChatMessageJpo> findByReceiverIdAndRead(String receiverId, boolean read);
    int countByReceiverIdAndRead(String receiverId, boolean read);
}