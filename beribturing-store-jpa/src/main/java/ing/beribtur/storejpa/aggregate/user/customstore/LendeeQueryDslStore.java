package ing.beribtur.storejpa.aggregate.user.customstore;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.feature.adm.lendee.customstore.LendeeCustomStore;
import ing.beribtur.storejpa.aggregate.user.jpo.LendeeJpo;
import ing.beribtur.storejpa.util.QuerydslUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static ing.beribtur.storejpa.aggregate.user.jpo.QLendeeJpo.lendeeJpo;


@Repository
@RequiredArgsConstructor
public class LendeeQueryDslStore implements LendeeCustomStore {
    //
    private final QuerydslUtil querydslUtil;

    @Override
    public Page<Lendee> findLendees(String searchKeyword, String status, Offset offset) {
        //
        BooleanBuilder builder = new BooleanBuilder();
        builder.or(lendeeJpo.name.likeIgnoreCase("%" + searchKeyword + "%"))
                .or(lendeeJpo.phoneNumber.likeIgnoreCase("%" + searchKeyword + "%"));

        if (StringUtils.hasText(status)) {
            if (status.equals("Active")) {
                builder.and(lendeeJpo.active.isTrue());
            } else if (status.equals("InActive")) {
                builder.and(lendeeJpo.active.isFalse());
            }
        }

        OrderSpecifier<?>[] orderSpecifiers = {
                lendeeJpo.registeredOn.desc()
        };

        Page<LendeeJpo> lendeeJpoPage = querydslUtil.query(lendeeJpo, lendeeJpo, new Predicate[]{builder}, orderSpecifiers, offset);

        List<Lendee> lendees = lendeeJpoPage.getContent().stream()
                .map(LendeeJpo::toDomain)
                .toList();

        return new PageImpl<>(lendees, lendeeJpoPage.getPageable(), lendeeJpoPage.getTotalElements());
    }
}
