package ing.beribtur.aggregate.chat.store;


import ing.beribtur.aggregate.chat.entity.ChatMessage;

import java.util.List;

public interface ChatMessageStore {
    //
    void create(ChatMessage chatMessage);
    ChatMessage retrieve(String id);
    List<ChatMessage> retrieveAll(List<String> ids);
    void update(ChatMessage chatMessage);
    void delete(String id);
}
