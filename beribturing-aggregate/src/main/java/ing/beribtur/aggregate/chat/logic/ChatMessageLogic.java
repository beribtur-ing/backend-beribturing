package ing.beribtur.aggregate.chat.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatMessageLogic {
    //
    public int countUnreadMessageOfUser(String username) {
        return 0;
    }
}
