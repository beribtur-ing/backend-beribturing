package ing.beribtur.accent.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class QueryRequest<T> extends AbstractQuery<T> {
    //
    protected QueryRequest() {
        //
        super();
    }
}
