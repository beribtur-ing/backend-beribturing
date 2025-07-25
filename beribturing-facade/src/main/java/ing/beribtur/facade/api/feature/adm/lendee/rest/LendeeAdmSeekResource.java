package ing.beribtur.facade.api.feature.adm.lendee.rest;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.accent.util.QueryResponseUtil;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.facade.api.feature.adm.lendee.query.FindLendeeAdmQuery;
import ing.beribtur.facade.api.feature.adm.lendee.query.FindLendeesAdmQuery;
import ing.beribtur.feature.adm.lendee.seek.LendeeSeek;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feature/admin/lendee")
@RequiredArgsConstructor
public class LendeeAdmSeekResource implements LendeeAdmSeekFacade {
    private final LendeeSeek lendeeSeek;

    @Override
    @PostMapping("/find-lendees/query")
    public QueryResponse<List<Lendee>> findLendeesAdmin(@RequestBody FindLendeesAdmQuery query) {
        Offset offset = query.getOffset();
        String searchKeyword = query.getSearchKeyword();
        String status = query.getStatus();
        Page<Lendee> lendees = lendeeSeek.findLendeesAdmin(searchKeyword, status, offset);
        return QueryResponseUtil.fromPage(lendees);
    }

    @Override
    @PostMapping("/find-lendee/query")
    public QueryResponse<Lendee> findLendeeAdmin(@RequestBody FindLendeeAdmQuery query) {
        String lendeeId = query.getLendeeId();
        Lendee lendee = lendeeSeek.findLendeeAdmin(lendeeId);
        return new QueryResponse<>(lendee);
    }
}
