package ing.beribtur.aggregate.chat.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ChatMessageCdo extends CreationDataObject {
    private String senderId;
    private String receiverId;
    private String content;
    private LocalDateTime timestamp;
    private boolean isRead;
}
