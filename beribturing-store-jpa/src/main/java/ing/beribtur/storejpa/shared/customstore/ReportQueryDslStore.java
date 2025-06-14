package ing.beribtur.storejpa.shared.customstore;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPAExpressions;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.report.entity.Report;
import ing.beribtur.feature.shared.customstore.ReportCustomStore;
import ing.beribtur.storejpa.aggregate.report.jpo.ReportJpo;
import ing.beribtur.storejpa.util.QuerydslUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

import static ing.beribtur.storejpa.aggregate.rental.jpo.QRentalRecordJpo.rentalRecordJpo;
import static ing.beribtur.storejpa.aggregate.report.jpo.QReportJpo.reportJpo;


@Repository
@RequiredArgsConstructor
public class ReportQueryDslStore implements ReportCustomStore {
    //
    private final QuerydslUtil querydslUtil;

    @Override
    public Page<Report> findReportsByProductVariantId(String productVariantId, Offset offset) {
        //
        Predicate[] predicates = {
                reportJpo.recordId.in(
                        JPAExpressions
                                .select(rentalRecordJpo.id)
                                .from(rentalRecordJpo)
                                .where(rentalRecordJpo.productVariantId.eq(productVariantId))
                )
        };

        OrderSpecifier<?>[] orderSpecifiers = {
                reportJpo.registeredOn.desc()
        };

        Page<ReportJpo> reportJpoPage = querydslUtil.query(reportJpo, reportJpo, predicates, orderSpecifiers, offset);

        List<Report> reports = reportJpoPage.getContent().stream()
                .map(ReportJpo::toDomain)
                .toList();

        return new PageImpl<>(reports, reportJpoPage.getPageable(), reportJpoPage.getTotalElements());
    }
}
