package ing.beribtur.aggregate.chat.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageCdo extends CreationDataObject {
    private String senderId;
    private String receiverId;
    private String content;
    private LocalDateTime timestamp;
    private boolean read;
}
