package ing.beribtur.facade.feature.admin.rest;

import ing.beribtur.accent.message.QueryResponse;
import ing.beribtur.accent.util.QueryResponseUtil;
import ing.beribtur.aggregate.user.entity.Lender;
import ing.beribtur.facade.feature.admin.command.FindDisabledLendersAdminQuery;
import ing.beribtur.feature.lender.seek.LenderSeek;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lender")
@RequiredArgsConstructor
public class LenderSeekResource implements LenderSeekFacade {
    private final LenderSeek lenderSeek;

    @Override
    @PostMapping("/find-disabled-lenders-admin/query")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public QueryResponse<List<Lender>> findDisabledLendersAdmin(FindDisabledLendersAdminQuery query) {
        query.validate();
        int page = query.getPage();
        int size = query.getSize();
        String sort = query.getSort();
        Page<Lender> lenders = lenderSeek.findDisabledLendersAdmin(page, size, sort);
        return QueryResponseUtil.fromPage(lenders);
    }
}
