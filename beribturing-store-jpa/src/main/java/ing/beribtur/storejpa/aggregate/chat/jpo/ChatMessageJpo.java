package ing.beribtur.storejpa.aggregate.chat.jpo;

import ing.beribtur.accent.domain.DomainEntityJpo;
import ing.beribtur.aggregate.chat.entity.ChatMessage;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CHAT_MESSAGE")
public class ChatMessageJpo extends DomainEntityJpo {

    @Column(nullable = false)
    private String senderId;

    @Column(nullable = false)
    private String receiverId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    private boolean read;

    public ChatMessageJpo(ChatMessage chatMessage) {
        //
        super(chatMessage);
        BeanUtils.copyProperties(chatMessage, this);
    }

    public ChatMessage toDomain() {
        //
        ChatMessage chatMessage = new ChatMessage();
        BeanUtils.copyProperties(this, chatMessage);
        return chatMessage;
    }

    public static List<ChatMessage> toDomains(List<ChatMessageJpo> jpos) {
        //
        return jpos.stream().map(ChatMessageJpo::toDomain).toList();
    }
}