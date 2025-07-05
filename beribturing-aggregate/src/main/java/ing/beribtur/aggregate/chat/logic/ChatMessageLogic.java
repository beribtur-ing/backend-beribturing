package ing.beribtur.aggregate.chat.logic;


import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.chat.entity.ChatMessage;
import ing.beribtur.aggregate.chat.entity.sdo.ChatMessageCdo;
import ing.beribtur.aggregate.chat.store.ChatMessageStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatMessageLogic {
    //
    private final ChatMessageStore chatMessageStore;

    public String registerChatMessage(ChatMessageCdo chatMessageCdo) {
        //
        ChatMessage chatMessage = new ChatMessage(chatMessageCdo);
        chatMessageStore.create(chatMessage);
        return chatMessage.getId();
    }

    public List<String> registerChatMessages(List<ChatMessageCdo> chatMessageCdos) {
        List<ChatMessage> chatMessages = chatMessageCdos.stream()
                .map(ChatMessage::new)
                .toList();
        chatMessageStore.createAll(chatMessages);
        return chatMessages.stream()
                .map(ChatMessage::getId)
                .toList();
    }

    public ChatMessage findChatMessage(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ChatMessage id is required");
        }
        return chatMessageStore.retrieve(id);
    }

    public List<ChatMessage> findChatMessages(Offset offset) {
        return chatMessageStore.retrieveList(offset);
    }

    public void modifyChatMessage(String id, NameValueList nameValues) {
        ChatMessage chatMessage = findChatMessage(id);
        chatMessage.modifyAttributes(nameValues);
        chatMessageStore.update(chatMessage);
    }

    public void modifyChatMessage(ChatMessage chatMessage) {
        chatMessageStore.update(chatMessage);
    }

    public void removeChatMessage(String id) {
        if (!existsChatMessage(id)) {
            throw new IllegalArgumentException("ChatMessage not found: " + id);
        }
        chatMessageStore.delete(id);
    }

    public boolean existsChatMessage(String id) {
        return chatMessageStore.exists(id);
    }

    // Chat-specific business methods
    public List<ChatMessage> findConversation(String senderId, String receiverId) {
        return chatMessageStore.findBySenderIdAndReceiverId(senderId, receiverId);
    }

    public List<ChatMessage> findMessagesBySender(String senderId) {
        return chatMessageStore.findBySenderId(senderId);
    }

    public List<ChatMessage> findMessagesByReceiver(String receiverId) {
        return chatMessageStore.findByReceiverId(receiverId);
    }

    public List<ChatMessage> findUnreadMessages(String receiverId) {
        return chatMessageStore.findUnreadByReceiverId(receiverId);
    }

    public int countUnreadMessageOfUser(String username) {
        //
        return chatMessageStore.countUnreadMessagesByReceiverId(username);
    }

    public void markAsRead(String messageId) {
        //
        ChatMessage chatMessage = findChatMessage(messageId);
        NameValueList nameValues = new NameValueList();
        nameValues.add(NameValue.of("read", "true"));
        chatMessage.modifyAttributes(nameValues);
        chatMessageStore.update(chatMessage);
    }

    public void markAllAsRead(String receiverId) {
        //
        List<ChatMessage> unreadMessages = findUnreadMessages(receiverId);
        for (ChatMessage message : unreadMessages) {
            NameValueList nameValues = new NameValueList();
            nameValues.add(NameValue.of("read", "true"));
            message.modifyAttributes(nameValues);
            chatMessageStore.update(message);
        }
    }
}
