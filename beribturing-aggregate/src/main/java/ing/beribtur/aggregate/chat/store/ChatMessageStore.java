package ing.beribtur.aggregate.chat.store;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.chat.entity.ChatMessage;

import java.util.List;

public interface ChatMessageStore {
    //
    void create(ChatMessage chatMessage);
    void createAll(List<ChatMessage> chatMessages);
    ChatMessage retrieve(String id);
    List<ChatMessage> retrieveAll(List<String> ids);
    List<ChatMessage> retrieveList(Offset offset);
    void update(ChatMessage chatMessage);
    void delete(ChatMessage chatMessage);
    void delete(String id);
    boolean exists(String id);

    // Chat-specific query methods
    List<ChatMessage> findBySenderId(String senderId);
    List<ChatMessage> findByReceiverId(String receiverId);
    List<ChatMessage> findBySenderIdAndReceiverId(String senderId, String receiverId);
    List<ChatMessage> findUnreadByReceiverId(String receiverId);
    int countUnreadMessagesByReceiverId(String receiverId);
}
