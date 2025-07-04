package ing.beribtur.facade.api.feature.own.user.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.feature.own.user.seek.UserOwnSeek;
import ing.beribtur.feature.shared.sdo.UserMeRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feature/owner/user")
@RequiredArgsConstructor
public class UserOwnSeekResource implements UserOwnSeekFacade {
    private final UserOwnSeek userOwnSeek;

    @Override
    @PostMapping("/me/query")
    public QueryResponse<UserMeRdo> userMe() {
        UserMeRdo rdo = userOwnSeek.userMe();
        return new QueryResponse<>(rdo);
    }
}
