package ing.beribtur.facade.api.feature.adm.user.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.feature.adm.user.seek.UserAdmSeek;
import ing.beribtur.feature.shared.sdo.UserMeRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feature/admin/user")
@RequiredArgsConstructor
public class UserAdmSeekResource implements UserAdmSeekFacade {
    //
    private final UserAdmSeek userAdmSeek;

    @Override
    @PostMapping("/me/query")
    public QueryResponse<UserMeRdo> userMe() {
        UserMeRdo rdo = userAdmSeek.userMe();
        return new QueryResponse<>(rdo);
    }
}
