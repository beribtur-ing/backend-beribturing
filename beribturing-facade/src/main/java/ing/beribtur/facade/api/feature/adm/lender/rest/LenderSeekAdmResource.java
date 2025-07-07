package ing.beribtur.facade.api.feature.adm.lender.rest;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.accent.util.QueryResponseUtil;
import ing.beribtur.aggregate.user.entity.Lender;
import ing.beribtur.facade.api.feature.adm.lender.query.FindLenderAdmQuery;
import ing.beribtur.facade.api.feature.adm.lender.query.FindLendersAdmQuery;
import ing.beribtur.feature.adm.lender.seek.LenderSeek;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feature/admin/lender")
@RequiredArgsConstructor
public class LenderSeekAdmResource implements LenderAdmSeekFacade {
    private final LenderSeek lenderSeek;

    @Override
    @PostMapping("/find-lenders/query")
    public QueryResponse<List<Lender>> findLendersAdmin(@RequestBody FindLendersAdmQuery query) {
        Offset offset = query.getOffset();
        String searchKeyword = query.getSearchKeyword();
        String status = query.getStatus();
        Page<Lender> lenders = lenderSeek.findLendersAdmin(searchKeyword, status, offset);
        return QueryResponseUtil.fromPage(lenders);
    }

    @Override
    @PostMapping("/find-lender/query")
    public QueryResponse<Lender> findLenderAdmin(@RequestBody FindLenderAdmQuery query) {
        String lenderId = query.getLenderId();
        Lender lender = lenderSeek.findLenderAdmin(lenderId);
        return new QueryResponse<>(lender);
    }
}
