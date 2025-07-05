package ing.beribtur.storejpa.aggregate.chat;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.chat.entity.ChatMessage;
import ing.beribtur.aggregate.chat.store.ChatMessageStore;
import ing.beribtur.storejpa.aggregate.chat.jpo.ChatMessageJpo;
import ing.beribtur.storejpa.aggregate.chat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ChatMessageJpaStore implements ChatMessageStore {
    //
    private final ChatMessageRepository chatMessageRepository;

    @Override
    public void create(ChatMessage chatMessage) {
        ChatMessageJpo chatMessageJpo = new ChatMessageJpo(chatMessage);

        chatMessageRepository.save(chatMessageJpo);
        chatMessage.setId(chatMessageJpo.getId());
    }

    @Override
    public void createAll(List<ChatMessage> chatMessages) {
        List<ChatMessageJpo> chatMessageJpos = chatMessages.stream()
                .map(ChatMessageJpo::new)
                .collect(Collectors.toList());

        List<ChatMessageJpo> savedJpos = chatMessageRepository.saveAll(chatMessageJpos);

        for (int i = 0; i < chatMessages.size(); i++) {
            chatMessages.get(i).setId(savedJpos.get(i).getId());
        }
    }

    @Override
    public ChatMessage retrieve(String id) {
        ChatMessageJpo chatMessageJpo = chatMessageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ChatMessage not found: " + id));
        return chatMessageJpo.toDomain();
    }

    @Override
    public List<ChatMessage> retrieveAll(List<String> ids) {
        List<ChatMessageJpo> chatMessageJpos = chatMessageRepository.findAllById(ids);
        return chatMessageJpos.stream()
                .map(ChatMessageJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatMessage> retrieveList(Offset offset) {
        Pageable pageable = PageRequest.of(offset.page(), offset.limit(),
                Sort.by(Sort.Direction.DESC, "registeredOn"));
        return chatMessageRepository.findAll(pageable)
                .getContent()
                .stream()
                .map(ChatMessageJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void update(ChatMessage chatMessage) {
        ChatMessageJpo chatMessageJpo = chatMessageRepository.findById(chatMessage.getId())
                .orElseThrow(() -> new IllegalArgumentException("ChatMessage not found: " + chatMessage.getId()));

        // Update the JPO with the domain entity's values
        ChatMessageJpo updatedJpo = new ChatMessageJpo(chatMessage);
        updatedJpo.setEntityVersion(chatMessageJpo.getEntityVersion());
        updatedJpo.setRegisteredBy(chatMessageJpo.getRegisteredBy());
        updatedJpo.setRegisteredOn(chatMessageJpo.getRegisteredOn());

        chatMessageRepository.save(updatedJpo);
    }

    @Override
    public void delete(ChatMessage chatMessage) {
        chatMessageRepository.delete(new ChatMessageJpo(chatMessage));
    }

    @Override
    public void delete(String id) {
        chatMessageRepository.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        return chatMessageRepository.existsById(id);
    }

    // Chat-specific query methods
    @Override
    public List<ChatMessage> findBySenderId(String senderId) {
        return ChatMessageJpo.toDomains(chatMessageRepository.findBySenderId(senderId));
    }

    @Override
    public List<ChatMessage> findByReceiverId(String receiverId) {
        return ChatMessageJpo.toDomains(chatMessageRepository.findByReceiverId(receiverId));
    }

    @Override
    public List<ChatMessage> findBySenderIdAndReceiverId(String senderId, String receiverId) {
        return ChatMessageJpo.toDomains(chatMessageRepository.findBySenderIdAndReceiverId(senderId, receiverId));
    }

    @Override
    public List<ChatMessage> findUnreadByReceiverId(String receiverId) {
        return ChatMessageJpo.toDomains(chatMessageRepository.findByReceiverIdAndRead(receiverId, false));
    }

    @Override
    public int countUnreadMessagesByReceiverId(String receiverId) {
        //
        return chatMessageRepository.countByReceiverIdAndRead(receiverId, false);
    }

    // Additional helper method for backward compatibility
    public List<ChatMessage> findByIsRead(boolean isRead) {
        return ChatMessageJpo.toDomains(chatMessageRepository.findByRead(isRead));
    }
}
