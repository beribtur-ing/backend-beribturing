package ing.beribtur.accent.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractQuery<T> {
    //
    private Offset offset;

    protected AbstractQuery() {
        //
        this.offset = Offset.newUnlimited();
    }

    protected AbstractQuery(Offset offset) {
        //
        this.offset = offset;
    }
}
