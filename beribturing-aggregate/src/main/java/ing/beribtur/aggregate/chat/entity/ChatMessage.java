package ing.beribtur.aggregate.chat.entity;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.user.entity.vo.Communicable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage extends DomainEntity {
    //
    private String senderId;
    private String receiverId;
    private String content;
    private LocalDateTime timestamp;
    private boolean isRead;

    private transient Communicable sender;
    private transient Communicable receiver;

    @Override
    protected void modifyAttributes(NameValueList var1) {

    }
}
