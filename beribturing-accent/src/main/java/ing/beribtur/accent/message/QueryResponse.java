package ing.beribtur.accent.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class QueryResponse<T> {
    //
    private T result;
    private Offset offset;

    public QueryResponse(T queryResult) {
        //
        this.result = queryResult;
    }

    public QueryResponse(Offset offset) {
        //
        this.offset = offset;
    }
}
