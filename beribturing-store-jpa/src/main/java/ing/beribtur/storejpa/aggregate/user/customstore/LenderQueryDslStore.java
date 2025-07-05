package ing.beribtur.storejpa.aggregate.user.customstore;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.report.entity.Report;
import ing.beribtur.aggregate.user.entity.Lender;
import ing.beribtur.feature.adm.lender.customstore.LenderCustomStore;
import ing.beribtur.storejpa.aggregate.report.jpo.ReportJpo;
import ing.beribtur.storejpa.aggregate.user.jpo.LenderJpo;
import ing.beribtur.storejpa.util.QuerydslUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static ing.beribtur.storejpa.aggregate.rental.jpo.QRentalRecordJpo.rentalRecordJpo;
import static ing.beribtur.storejpa.aggregate.report.jpo.QReportJpo.reportJpo;
import static ing.beribtur.storejpa.aggregate.user.jpo.QLenderJpo.lenderJpo;


@Repository
@RequiredArgsConstructor
public class LenderQueryDslStore implements LenderCustomStore {
    //
    private final QuerydslUtil querydslUtil;

    @Override
    public Page<Lender> findLenders(String searchKeyword, String status, Offset offset) {
        //
        BooleanBuilder builder = new BooleanBuilder();
        builder.or(lenderJpo.name.likeIgnoreCase("%" + searchKeyword + "%"))
                .or(lenderJpo.phoneNumber.likeIgnoreCase("%" + searchKeyword + "%"));

        if (StringUtils.hasText(status)) {
            if (status.equals("Active")) {
                builder.and(lenderJpo.active.isTrue());
            } else if (status.equals("InActive")) {
                builder.and(lenderJpo.active.isFalse());
            }
        }

        OrderSpecifier<?>[] orderSpecifiers = {
                lenderJpo.registeredOn.desc()
        };

        Page<LenderJpo> lenderJpoPage = querydslUtil.query(lenderJpo, lenderJpo, new Predicate[]{builder}, orderSpecifiers, offset);

        List<Lender> lenders = lenderJpoPage.getContent().stream()
                .map(LenderJpo::toDomain)
                .toList();

        return new PageImpl<>(lenders, lenderJpoPage.getPageable(), lenderJpoPage.getTotalElements());
    }
}
