package ing.beribtur.storejpa.util;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ing.beribtur.accent.message.Offset;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class QuerydslUtil {
    //
    private final JPAQueryFactory jpaQueryFactory;


    public static BooleanBuilder isNullIfTrue(StringPath path, Boolean value) {
        //
        BooleanBuilder builder = new BooleanBuilder();
        if (Boolean.TRUE == value) {
            builder.and(path.isNull());
        }
        return builder;
    }

    public static <T extends String> BooleanBuilder eqIfNotNull(StringPath path, T value) {
        //
        BooleanBuilder builder = new BooleanBuilder();
        if (value != null) {
            builder.and(path.eq(value));
        }
        return builder;
    }

    public static <T extends Boolean> BooleanBuilder eqIfNotNull(BooleanPath path, T value) {
        //
        BooleanBuilder builder = new BooleanBuilder();
        if (value != null) {
            builder.and(path.eq(value));
        }
        return builder;
    }

    public static <T extends Number & Comparable<?>> BooleanBuilder eqIfNotNull(NumberPath<T> path, T value) {
        //
        BooleanBuilder builder = new BooleanBuilder();
        if (value != null) {
            builder.and(path.eq(value));
        }
        return builder;
    }

    public static <T extends Enum<T>> BooleanBuilder eqIfNotNull(EnumPath<T> path, T value) {
        //
        BooleanBuilder builder = new BooleanBuilder();
        if (value != null) {
            builder.and(path.eq(value));
        }
        return builder;
    }

    public static <T extends Enum<T>> BooleanBuilder inIfNotNull(EnumPath<T> path, List<T> values) {
        //
        BooleanBuilder builder = new BooleanBuilder();
        if (!CollectionUtils.isEmpty(values)) {
            builder.and(path.in(values));
        }
        return builder;
    }

    public static BooleanBuilder inIfNotNull(StringPath path, List<String> values) {
        //
        BooleanBuilder builder = new BooleanBuilder();
        if (!CollectionUtils.isEmpty(values)) {
            builder.and(path.in(values));
        }
        return builder;
    }

    public static <T extends Number & Comparable<T>> BooleanBuilder inIfNotNull(NumberPath<T> path, List<T> values) {
        //
        BooleanBuilder builder = new BooleanBuilder();
        if (!CollectionUtils.isEmpty(values)) {
            builder.and(path.in(values));
        }
        return builder;
    }

    public static BooleanBuilder likeIfNotNull(StringPath path, String value) {
        //
        BooleanBuilder builder = new BooleanBuilder();
        if (value != null) {
            builder.and(path.lower().like("%" + value.toLowerCase() + "%"));
        }
        return builder;
    }

    public <T> Page<T> query(EntityPathBase<T> select, EntityPath<?> from, Predicate[] predicates, OrderSpecifier<?>[] orderSpecifiers, Offset offset) {
        //
        Long totalCount = jpaQueryFactory.select(select.countDistinct())
            .from(from)
            .where(predicates)
            .fetchOne();

        if (totalCount == null || totalCount == 0L) {
            return new PageImpl<>(Collections.emptyList(), PageRequest.of(offset.page(), offset.limit()), 0);
        }

        List<T> results = jpaQueryFactory.select(select)
            .from(from)
            .where(predicates)
            .offset(offset.offset())
            .limit(offset.limit())
            .orderBy(orderSpecifiers)
            .fetch();

        return new PageImpl<>(results, PageRequest.of(offset.page(), offset.limit()), totalCount);
    }

    public <T> Page<T> query(ConstructorExpression<T> select, JPAQuery<?> from, Predicate[] predicates, OrderSpecifier<?>[] orderSpecifiers, Offset offset) {
        //
        Long totalCount = from
            .where(predicates)
            .fetchCount();

        if (totalCount == null || totalCount == 0L) {
            return new PageImpl<>(Collections.emptyList(), PageRequest.of(offset.page(), offset.limit()), 0);
        }

        List<T> results = from.select(select)
            .where(predicates)
            .offset(offset.offset())
            .limit(offset.limit())
            .orderBy(orderSpecifiers)
            .fetch();

        return new PageImpl<>(results, PageRequest.of(offset.page(), offset.limit()), totalCount);
    }
}
