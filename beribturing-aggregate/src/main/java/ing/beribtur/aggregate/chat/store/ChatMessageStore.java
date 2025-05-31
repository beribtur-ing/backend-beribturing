package ing.beribtur.aggregate.chat.store;


import ing.beribtur.aggregate.chat.entity.ChatMessage;

import java.util.List;

public interface ChatMessageStore {
    //
    ChatMessage create(ChatMessage chatMessage);
    ChatMessage retrieve(String id);
    List<ChatMessage> retrieveAll(List<String> ids);
    ChatMessage update(ChatMessage chatMessage);
    void delete(String id);
}
