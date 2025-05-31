package ing.beribtur.accent.util;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.message.QueryResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class QueryResponseUtil {
    public static <T> QueryResponse<List<T>> fromPage(Page<T> page) {
        //
        QueryResponse<List<T>> response = new QueryResponse<>();
        response.setResult(page.getContent());
        Offset offset = new Offset(page.getNumber(), page.getSize());
        offset.setTotalCount(page.getTotalElements());
        offset.setPrevious(page.hasPrevious());
        offset.setNext(page.hasNext());
        response.setOffset(offset);
        return response;
    }
}
